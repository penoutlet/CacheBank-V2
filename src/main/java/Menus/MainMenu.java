package Menus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import items.Account;
import items.ApprovedAccounts;
import items.PendingAccounts;
import utilities.Dummies;

public class MainMenu {
	static Scanner sc;
	static String[] optionsArray = { "1. Customer", "2. Employee", "3. Admin", "4. Return to login menu." };

	public static void firstLoginMenu() {
		Dummies.addDummies();
		sc = new Scanner(System.in);
		boolean flag = true;
		System.out.println("Welcome to Stacks of Cache Bank.");
		System.out.println("1. Sign up.\n2. Sign in.");
		while (flag) {

			String newUser = sc.nextLine();
			switch (newUser) {
			case "0":
				try {
					ApprovedAccounts.deserialize();
				} catch(FileNotFoundException f) {
					f.printStackTrace();
				}
				firstLoginMenu();
				flag=!flag;
				break;
			case "1":
				createNewUser();
				flag = !flag;
				break;
			case "2":
				mainMenu();
				flag = !flag;
				break;
//			case "3":
//				Dummies.addDummies();
//				firstLoginMenu();
//				flag = !flag;
//				break;
			default:
				firstLoginMenu();
				flag = !flag;
				break;
			}
		}
	}

	public static void mainMenu() {
		for (String s : optionsArray) {
			System.out.println(s);
		}
		System.out.println("Please select an option to continue");
		String choice = sc.nextLine();
		loginHandler(choice);
	}

	public static void loginHandler(String choice) {
		boolean flag = true;
		while (flag) {

			switch (choice) {
			case "1":
				CustomerMenu.loginMenu();
				flag = !flag;
				break;
			case "2":
				ApprovedAccounts.employeeLogin();
				flag = !flag;
				break;
			case "3":
				ApprovedAccounts.adminLogin();
				flag = !flag;
				break;
			case "4":
				firstLoginMenu();
			default:
				System.out.println("Select a choice.");
				MainMenu.mainMenu();
				flag = !flag;
				break;
			}
		}
	}

	public static Account createNewUser() {
		sc = new Scanner(System.in);
		Account newAccount = null;
		System.out.println("Enter a username.");
		String username = sc.nextLine();
		System.out.println("Enter a password.");
		String pw = sc.nextLine();
		System.out.println("Enter first name.");
		String firstname = sc.nextLine();
		System.out.println("Enter last name.");
		String lastname = sc.nextLine();
		System.out.println("Enter a balance");
		double balance = sc.nextDouble();
		System.out.println("Joint account? 1 for yes, 2 for no.");
		sc = new Scanner(System.in);
		String joint = sc.nextLine();
		switch (joint) {
		case "1":
			System.out.println("Enter a second first name");
			String jointFirst = sc.nextLine();
			System.out.println("Enter a second last name");
			String jointLast = sc.nextLine();
			newAccount = new Account(username, pw, balance, firstname, lastname, jointFirst, jointLast);
//			PendingAccounts.addOne(jointUser, newAccount); // adds an extra duplicate account as value
			break;
		case "2":
			newAccount = new Account(username, pw, balance, firstname, lastname);

		}
		PendingAccounts.addOne(username, newAccount);
		MainMenu.firstLoginMenu();
		return newAccount;
	}
}
