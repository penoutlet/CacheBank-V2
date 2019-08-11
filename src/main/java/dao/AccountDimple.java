package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.Account;

public class AccountDimple implements AccountDAO {
	static Connection conn;
	private static String url = "jdbc:oracle:thin:@samjavadb.cecxuvq3cw6r.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "sambo";
	private static String password = "sambo";
	@Override
	public void insertAccount(Account a) {
		try(Connection conn = DriverManager.getConnection(url, username,password)){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO accounts VALUES (null,?,?,?)");
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getIsApproved());
			ps.setString(3, a.getTempToken());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account findAccountByAID(String aid) {
		Account a = null;
		try(Connection conn = DriverManager.getConnection(url,username,password)){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM accounts WHERE aid =?");
			ps.setString(1, aid);
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				//temp token field is null, because it's obsolete to know after signing up
				a = new Account(rs.getString("aid"),rs.getDouble("balance"),rs.getInt("isApproved"),null);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<Account> findAllAccounts() {
		return null;
	}

	@Override
	public void updateAccountBalance(Account a) {
		try(Connection conn = DriverManager.getConnection(url,username,password)){
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET balance = ? WHERE aid =?");
			ps.setDouble(1, a.getBalance());
			ps.setString(2, a.getAID());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAccount(Account a) {
		try(Connection conn = DriverManager.getConnection(url,username,password)){
			PreparedStatement ps = conn.prepareStatement("DELETE FROM accounts WHERE aid =?");
			ps.setString(1, a.getAID());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAccountApproval(Account a) {
		try(Connection conn = DriverManager.getConnection(url,username,password)){
			PreparedStatement ps = conn.prepareStatement("UPDATE accounts SET isapproved=1 WHERE aid= ?");
			ps.setString(1, a.getAID());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
