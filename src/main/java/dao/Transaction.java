package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import beans.Account;
import beans.User;
import menus.AdminMenu;
import menus.CustomerMenu;
import menus.MainMenu;

public class Transaction {
	static Connection conn;
	private static String url = "jdbc:oracle:thin:@samjavadb.cecxuvq3cw6r.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "sambo";
	private static String password = "sambo";
	AccountDimple adi = new AccountDimple();
	UserDimple udi = new UserDimple();
	MainMenu mainMenu = new MainMenu();
	CustomerMenu custMenu = new CustomerMenu();
	AdminMenu adminMenu = new AdminMenu();
	private static Logger log = Logger.getLogger(Transaction.class);
	
	
	public void insertAdmins() {
		if(udi.findUserByUsername("admin")==null) {
			User u = new User("admin","admin");
			createUser(u);
			if(udi.findUserByUsername("emp")==null) {
				u = new User("emp","emp");
				createUser(u);
			}
		}
//		else if(udi.findUserByUsername("Sam")==null) {
//			User u = new User("Sam","pass");
//			createUser(u);
//			if(udi.findUserByUsername("Carol")==null) {
//				u = new User("Carol","pass");
//				createUser(u);
//				if(udi.findUserByUsername("Dan")==null) {
//					u = new User("Dan","pass");
//					createUser
//				}
//			}
//		}
	}
	
	public User login(String un, String pw) {
		User user = findUser(un);
		
		return user;
	}

	public void createUser(User u) {
		User alreadyExists = udi.findUserByUsername(u.getUsername());
		if(alreadyExists!=null) {
			System.out.println("Username already in use.");
			mainMenu.createNewUser();
		}
		udi.insertUser(u);
		log.info("User " + u.getUsername() + " created.");
	}

	public User findUser(String un) {
		User user = null;
		user = udi.findUserByUsername(un);
		return user;
	}
	public List<String> findAllUsernames(){
		List<String> users = udi.findAllUsernames();
		return users;
	}
	// methods for the junction table (lookuptable)-------------------
	public String findAIDByUsername(String un) {
		String aid = null;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM junctiontable WHERE username = ?");
			ps.setString(1, un);
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				aid = rs.getString("aid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aid;
	}
	
	public String findAIDByToken(String token) {
		String aid = null;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts WHERE temptoken = ?");
			ps.setString(1, token);
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				aid = rs.getString("aid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aid;
	}
	public void eraseTempToken(String aid) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET temptoken=null WHERE aid=?");
			ps.setString(1, aid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// this method will replace the one above, since it does not depend on a sequence existing for the
	// junction table.
	public void insertIntoJunction(String un,String aid) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO junctiontable VALUES (?,?)");
			ps.setString(1, un);
			ps.setString(2, aid);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// methods for accounts -- deposit, withdraw, transfer, approve & delete
	public static Boolean checkApproved(Account a) {
		if (a.getIsApproved()==0) {
			System.out.println("Note: Account is not approved, transactions with it are prohibited.");
			return false;
		}

		return true;
	}

	public void createAccount(Account a) {
		adi.insertAccount(a);
		log.info("Account created.");
	}
	public Account findAccountByAID(String aid) {
		return adi.findAccountByAID(aid);
	}
	public void deposit(String aid, Double amount) {
		Account a = adi.findAccountByAID(aid);
		if (!checkApproved(a)) {
			return;
		}
		else if(amount<0) {
			System.out.println("Negative deposits prohibited.");
			return;
		}
		Double currBalance = a.getBalance();
		Double newBalance = currBalance + amount;
		a.setBalance(newBalance);
		adi.updateAccountBalance(a);
		log.info(amount + " deposited into account " + a.getAID());

	}

	public void withdraw(String aid, Double amount) {
		Account a = adi.findAccountByAID(aid);
		if (!checkApproved(a)) {
			return;
		}
		else if(amount<0) {
			System.out.println("Negative withdrawals not allowed.");
			return;
		}
		Double currBalance = a.getBalance();
		if ((currBalance - amount > 0)) {
			Double newBalance = currBalance - amount;
			a.setBalance(newBalance);
			adi.updateAccountBalance(a);
			log.info(amount + " withdrawn from account " + a.getAID());
			System.out.println("Withdrawal Successful");

		} else {
			System.out.println("Insufficient funds to withdraw.");
		}
	}

	public void deleteAccountByAID(String aid) {
		Account a = adi.findAccountByAID(aid);
		adi.deleteAccount(a);
		log.info("Account " + a.getAID() + " destroyed.");
		
	}

	public  void approveAccount(Account a) {
		adi.updateAccountApproval(a);
		log.info("Account " + a.getAID() + " approved.");
	}

	public void transferAmount(String aid1, String aid2, Double amount) {
		Account a1 = adi.findAccountByAID(aid1);
		Account a2 = adi.findAccountByAID(aid2);
		if(!checkApproved(a1)) {
			System.out.println("Sender of transfer is not approved.");
			return;
		}
		else if(!checkApproved(a2)) {
			System.out.println("Recipient of transfer is not approved.");
			return;
		}
		else if(aid1.equals(aid2)) {
			System.out.println("Cannot transfer to a joint account holder.");
			return;
		}
		else if(amount<0) {
			System.out.println("Transfer amounts less than zero are prohibited.");
			return;
		}
		Double balanceOne = a1.getBalance();
		Double balanceTwo = a2.getBalance();
		if ((balanceOne - amount) > 0) {
			a1.setBalance(balanceOne - amount); // subtract the amount from the first account
			a2.setBalance(balanceTwo + amount); // add the amount to the second balance
			adi.updateAccountBalance(a1);
			adi.updateAccountBalance(a2);
			log.info(amount + " transferred from account " + a1.getAID() + " to accound " + a2.getAID() );

		} else {
			System.out.println("Insufficient funds to complete transfer.");
		}

	}

}
