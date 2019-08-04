package Menus;

import java.util.Scanner;

import Users.Employee;
import items.Account;
import items.ApprovedAccounts;
import items.PendingAccounts;

public class EmployeeMenu {
	static Scanner sc = new Scanner(System.in);
	static String[] optionsArray = { "1. View an approved account", "2. Approve or deny a pending account.",
			"3. Return to previous menu (logout)." };

	public static void actionMenu() {
		System.out.println("What would you like to do?");

		for (int i = 0; i < optionsArray.length; i++) {
			System.out.println(optionsArray[i]);
		}
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		handleInput(choice);
	}

	public static void handleInput(String choice) {
		Account account = null;
		String username = "";
		sc = new Scanner(System.in);
		boolean flag = true;
		while (flag) {

			switch (choice) {
			case "1":
				//view 1 approved
				System.out.println("Enter a username to fetch.");
//				sc.nextLine();
				username += sc.nextLine();
				account = ApprovedAccounts.fetchOne(username);
				if (account == null) {
					System.out.println("Account not found.");
					actionMenu();
				} 
				System.out.println(account.toString());
				actionMenu();
				flag = !flag;
				break;
			case "2":
				System.out.println("Enter a username to fetch.");
				username += sc.nextLine();
				account = PendingAccounts.fetchOne(username);
				if(account != null) {
					System.out.println(account.toString());
					approveOrDenyMenu(account);
				}
				System.out.println("No user found.");
				actionMenu();
				flag=!flag;
				break;
			case "3":
				MainMenu.mainMenu();
				flag = !flag;
				break;
			default:
				System.out.println("Enter 1, 2, or 3.");
				flag = !flag;
				break;
			}
		}
	}

	public static void approveOrDenyMenu(Account a) {
		System.out.println("1. Approve\n2. Deny\n3.Return to previous menu");
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		switch (choice) {
		case "1":
			Employee.approve(a);
			actionMenu();
			break;
		case "2":
			Employee.deny(a);
			actionMenu();
			break;
		case "3":
			actionMenu();
			break;
		default:
			approveOrDenyMenu(a);
			break;
		}
	}
}
