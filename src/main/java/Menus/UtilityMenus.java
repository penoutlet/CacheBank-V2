package menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.Account;
import beans.User;
import dao.Transaction;

public class UtilityMenus {
	static Scanner sc = new Scanner(System.in);
	CustomerMenu custMenu = new CustomerMenu();
	AdminMenu adminMenu = new AdminMenu();
	MainMenu mainMenu = new MainMenu();

	public UtilityMenus() {
		// TODO Auto-generated constructor stub
	}

	public void depositMenu(Account a) {
		sc = new Scanner(System.in);
		System.out.println("How much would you like to deposit? ");
		
		double depositAmt = sc.nextDouble();
		if(depositAmt<0) {
			System.out.println("Negative deposit prohibited.");
			adminMenu.actionMenu(a);
		}
		else {
			Transaction t = new Transaction();
			t.deposit(a.getAID(), depositAmt);
			Account updated = t.findAccountByAID(a.getAID());
			adminMenu.actionMenu(updated);
			
		}
	}

	public void withdrawMenu(Account a) {
		Transaction t = new Transaction();

		sc = new Scanner(System.in);
		System.out.println("How much to withdraw?");
		Double withdrawalAmt = sc.nextDouble();
		if(withdrawalAmt<0) {
			System.out.println("Negative withdrawals prohibited.");
			adminMenu.actionMenu(a);
		}
		else {
			t.withdraw(a.getAID(), withdrawalAmt);
			Account updated = t.findAccountByAID(a.getAID());
			adminMenu.actionMenu(updated);
		}
	}

	public void transferMenu(Account a) {
		Transaction t = new Transaction();
		sc = new Scanner(System.in);
		System.out.println("How much to transfer?");
		Double transferAmt = sc.nextDouble();
		System.out.println("Transfer to who? Enter a username.");
		sc = new Scanner(System.in);
		String un = sc.nextLine();
		String aid = t.findAIDByUsername(un);
		Account transferTo = t.findAccountByAID(aid);

		if (transferTo != null) {
			if(transferAmt<0) {
				System.out.println("Negative transfers prohibited.");
				adminMenu.actionMenu(a);
			}
			else {
				t.transferAmount(a.getAID(), transferTo.getAID(), transferAmt);
				Account updated = t.findAccountByAID(a.getAID());
				adminMenu.actionMenu(updated);
			}
		} else {
			System.out.println("Cannot transfer to that user; user not found.");
			// CustomerMenu.actionMenu(a);
			adminMenu.actionMenu(a);
		}
	}

	public void cancelMenu(Account a) {
		Transaction t = new Transaction();

		sc = new Scanner(System.in);
		System.out.println("Are you sure? 1 for yes, 0 to return");
		String choice = sc.nextLine();
		switch (choice) {
		case "1":
			t.deleteAccountByAID(a.getAID());
			// System.out.println("You no longer have an account, returning to main menu.");
			adminMenu.mainMenu();
			break;
		case "2":
			adminMenu.actionMenu(a);
			break;
		default:
			System.out.println("Returning to safety");
			adminMenu.actionMenu(a);
			break;
		}
	}

	public void loginMenu() {
		Transaction t = new Transaction();
		sc = new Scanner(System.in);
		System.out.println("Enter username.");
		String un = sc.nextLine();
		System.out.println("Enter password");
		String pw = sc.nextLine();

		User user = t.login(un, pw);
		if (user == null) {
			System.out.println("No user found.");
			mainMenu.mainMenu();
		}
		String aid = t.findAIDByUsername(un);
		Account a = t.findAccountByAID(aid);
		String foundUn = user.getUsername();
		String foundPw = user.getPassword();
			adminMenu.mainMenu();
		}

	public void firstInputHandler(String choice) {
		boolean flag = true;
		sc = new Scanner(System.in);
		while (flag) {

			switch (choice) {
			case "1": // look up a user by user name, return their account info and the acc
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
				if (a == null) {
					System.out.println("No account exists.");
					adminMenu.mainMenu();
				}
//				System.out
//						.println("Username: " + user.getUsername() + ", AID: " + aid + ", balance: " + a.getBalance());

				if (!Transaction.checkApproved(a)) {
					approveOrDenyMenu(a);
				}

				adminMenu.actionMenu(a);
				flag = !flag;
				break;
			case "2":
				t = new Transaction();
				List<String> users = t.findAllUsernames();
				// List<User> users = udi.findAllUsers();
				if (users.isEmpty()) {
					System.out.println("Currently there are no users.");
				}
				System.out.println("All users: ");
				for (String u : users) {
					System.out.print(u + " | ");
				}
				System.out.println();
				adminMenu.mainMenu();
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
			adminMenu.mainMenu();
			break;
		case "2":
			t = new Transaction();
			t.deleteAccountByAID(a.getAID());
			;
			adminMenu.mainMenu();
			break;
		case "3":
			adminMenu.mainMenu();
			;
		}
	}
}
