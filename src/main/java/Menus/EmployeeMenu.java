package menus;

import java.util.List;
import java.util.Scanner;

import beans.Account;
import beans.User;
import dao.Transaction;

public class EmployeeMenu {
	static Scanner sc = new Scanner(System.in);
	MainMenu mainMenu = new MainMenu();
	AdminMenu adminMenu = new AdminMenu();
	
	static String[] optionsArray = {"1. Find an account.","2. View all usernames.","3. Return to prior menu (logout)."};

	public void mainMenu() {
		System.out.println("Employee, what would you like to do?");

		for (int i = 0; i < optionsArray.length; i++) {
			System.out.println(optionsArray[i]);
		}
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		firstInputHandler(choice);
	}
	
	public void loginMenu() {
		Transaction t = new Transaction();
		sc = new Scanner(System.in);
		System.out.println("Enter username.");
		String un = sc.nextLine();
		System.out.println("Enter password");
		String pw = sc.nextLine();

		User user = t.login(un, pw);
		if(user==null) {
			System.out.println("No user found.");
			mainMenu.mainMenu();
		}
		String aid = t.findAIDByUsername(un);
//		Account a = t.findAccountByAID(aid);
		String foundUn = user.getUsername();
		String foundPw = user.getPassword();
		if(!foundUn.equals("emp")) { // if username is not "emp", it is not the employee.
			System.out.println(foundUn + " is not an employee.");
			mainMenu.mainMenu();
		}
		else if(!pw.equals(foundPw)) { // if username is "emp," but the password is wrong, then no go.
			System.out.println("Incorrect password.");
			mainMenu.mainMenu();
		}
//		actionMenu(a);
		mainMenu();
	}
	public void firstInputHandler(String choice) {
		boolean flag = true;
		sc = new Scanner(System.in);
		while (flag) {

			switch (choice) {
			case "1": //look up a user by user name, return their account info and the acc
				System.out.println("Enter a username to fetch.");
				String un = sc.nextLine();

				Transaction t = new Transaction();
				User user = t.findUser(un);
				if (user==null) {
					System.out.println("No user exists.");
					adminMenu.mainMenu();
				}
				String aid = t.findAIDByUsername(user.getUsername());
				Account a = t.findAccountByAID(aid);
				if(a==null) {
					System.out.println("No account exists.");
					mainMenu();
				}
				System.out.println("Username: " + user.getUsername() + ", AID: " + aid + ", balance: " +  a.getBalance());
				
				if (!Transaction.checkApproved(a)) {
					approveOrDenyMenu(a);
				}
				
				mainMenu();
				flag = !flag;
				break;
			case "2":
				t = new Transaction();
				List<String> users = t.findAllUsernames();
				// List<User> users = udi.findAllUsers();
				if(users.isEmpty()) {
					System.out.println("Currently, there are no users.");
					mainMenu();
				}
				System.out.println("All users:");
				for (String u : users) {
					System.out.print(u + ", ");
					}
				System.out.println();
				mainMenu();
				flag = !flag;
				break;
			case "3":
				mainMenu.mainMenu();
				flag = !flag;
				break;
			}
		}
	}
	public void approveOrDenyMenu(Account a) {
		System.out.println("1. Approve\n2. Deny (Delete)\n3. Return to previous menu");
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		Transaction t = null;
		switch (choice) {
		case "1":
			t = new Transaction();
			t.approveAccount(a);
			mainMenu();
			break;
		case "2":
			t = new Transaction();
			t.deleteAccountByAID(a.getAID());
			;
			mainMenu();
			break;
		case "3":
			mainMenu();
			;
		}
	}
}
