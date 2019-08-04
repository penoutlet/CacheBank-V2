package utilities;

import java.util.Scanner;

import Menus.AdminMenu;
import Menus.CustomerMenu;
import Menus.MainMenu;
import items.Account;
import items.Actions;
import items.ApprovedAccounts;

public class UtilityMenus {
	static Scanner sc = new Scanner(System.in);

	public static void depositMenu(Account a, String returnMenu) {
		sc = new Scanner(System.in);
		System.out.println("How much would you like to deposit? ");
		double depositAmt = sc.nextDouble();
		Actions.deposit(a, depositAmt);
		if(returnMenu.equals("customer")) {
			CustomerMenu.actionMenu(a);
		}
		else if(returnMenu.equals("admin")) {
			AdminMenu.actionMenu(a);
		}
		else {
			//invalid argument supplied as a return menu
		}
	}

	public static void withdrawMenu(Account a, String returnMenu) {
		sc = new Scanner(System.in);
		System.out.println("How much to withdraw?");
		double withdrawalAmt = sc.nextDouble();
		Actions.withdraw(a, withdrawalAmt);
		if(returnMenu.equals("customer")) {
			CustomerMenu.actionMenu(a);
		}
		else if(returnMenu.equals("admin")) {
			AdminMenu.actionMenu(a);
		}
		else {
			//invalid argument supplied as a return menu
		}
	}

	public static void transferMenu(Account a, String returnMenu) {
		sc = new Scanner(System.in);
		System.out.println("How much to transfer?");
		double transferAmt = sc.nextDouble();
		System.out.println("Transfer to who? Enter a username.");
		sc = new Scanner(System.in);
		String username = sc.nextLine();
		Account transferTo = ApprovedAccounts.fetchOne(username);
		
		if (transferTo != null) {
			Actions.transfer(a, transferAmt, transferTo);
		} else {
			System.out.println("Cannot transfer to that user; user not found.");
//			CustomerMenu.actionMenu(a);
		}
		if(returnMenu.equals("customer")) {
			CustomerMenu.actionMenu(a);
		}
		else if(returnMenu.equals("admin")) {
			AdminMenu.actionMenu(a);
		}
		else {
			//invalid argument supplied as a return menu
		}
	}

	public static void cancelMenu(Account a, String returnMenu) {
		sc = new Scanner(System.in);
		System.out.println("Are you sure? 1 for yes, 0 to return");
		String choice = sc.nextLine();
		if(returnMenu.equals("customer")) {
			switch (choice) {
			case "1":
				Actions.cancel(a);
				System.out.println("You no longer have an account, returning to main menu.");
				MainMenu.mainMenu();
				break;
			case "2":
				CustomerMenu.actionMenu(a);
				break;
			default:
				System.out.println("Returning to safety");
				CustomerMenu.actionMenu(a);
				break;
			}
		}
		
		else if(returnMenu.equals("admin")) {
			switch (choice) {
			case "1":
				Actions.cancel(a);
				AdminMenu.mainMenu();
				break;
			case "2":
				AdminMenu.mainMenu();
				break;
			default:
				System.out.println("Returning to safety");
				AdminMenu.actionMenu(a);
				break;
			}
		}
	}
}
