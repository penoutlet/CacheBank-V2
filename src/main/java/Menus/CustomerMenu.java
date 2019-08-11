package menus;

import java.util.Scanner;

import beans.Account;
import beans.User;
import dao.Transaction;
import utilities.Handler;

public class CustomerMenu {
	static Scanner sc = new Scanner(System.in);
	MainMenu mainMenu = new MainMenu();
	private static String[] optionsArray = { "0. View your account.", "1. Deposit", "2. Withdraw", "3. Transfer",
			"4. Cancel an Account", "5. Return to main menu" };

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
		
		if(!pw.equals(foundPw)) {
			System.out.println("Incorrect password.");
			mainMenu.mainMenu();
		}
		else if(a==null) {
			System.out.println("You have no account. Sign up again from the main menu.");
			mainMenu.mainMenu();
		}
		actionMenu(a);
	}

	public void actionMenu(Account a) {

		System.out.println("Welcome customer, what would you like to do?");

		for (int i = 0; i < optionsArray.length; i++) {
			System.out.println(optionsArray[i]);
		}
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		actionHandler(choice, a);

	}

	public void actionHandler(String choice, Account a) {
		boolean flag = true;
		MainMenu mainMenu = new MainMenu();
		AdminMenu adminMenu = new AdminMenu();
		CustomerMenu custMenu = new CustomerMenu();
		UtilityMenus utilMenu = new UtilityMenus();

		while (flag) {

			switch (choice) {
			case "0":

				System.out.println("AID: " + a.getAID() + ", balance: " + a.getBalance());

				actionMenu(a);
				flag = !flag;
				break;
			case "1":
				depositMenu(a);
				flag = !flag;
				break;
			case "2":
				withdrawMenu(a);
				flag = !flag;
				break;
			case "3":
				transferMenu(a);
				flag = !flag;
				break;
			case "4":
				cancelMenu(a);
				flag = !flag;
				break;
			case "5":
				mainMenu.mainMenu();
				flag = !flag;
				break;
			default:
				System.out.println("Select an option.");
				flag = !flag;
				break;
			}
		}
	}

	public void depositMenu(Account a) {
		sc = new Scanner(System.in);
		System.out.println("How much would you like to deposit? ");
		double depositAmt = sc.nextDouble();
		if(depositAmt<0) {
			System.out.println("Negative deposit prohibited.");
			actionMenu(a);
		}
		Transaction t = new Transaction();
		t.deposit(a.getAID(), depositAmt);
		Account updated = t.findAccountByAID(a.getAID());
		actionMenu(updated);
	}

	public void withdrawMenu(Account a) {
		Transaction t = new Transaction();

		sc = new Scanner(System.in);
		System.out.println("How much to withdraw?");
		Double withdrawalAmt = sc.nextDouble();
		t.withdraw(a.getAID(), withdrawalAmt);
		Account updated = t.findAccountByAID(a.getAID());
		actionMenu(updated);
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
			t.transferAmount(a.getAID(), transferTo.getAID(), transferAmt);
		} else {
			System.out.println("Cannot transfer to that user; user not found.");
			// CustomerMenu.actionMenu(a);
		}
		Account updated = t.findAccountByAID(a.getAID());
		actionMenu(updated);
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
			mainMenu.mainMenu();
			break;
		case "2":
			actionMenu(a);
			break;
		default:
			System.out.println("Returning to safety");
			actionMenu(a);
			break;
		}
	}

}
