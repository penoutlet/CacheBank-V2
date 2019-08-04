package Menus;


import java.util.Scanner;

import items.Account;
import items.ApprovedAccounts;
import utilities.Handler;


public class CustomerMenu  {
	static Scanner sc = new Scanner(System.in);
	private static String[] optionsArray = {"0. View your account.", "1. Deposit", "2. Withdraw", "3. Transfer", "4. Cancel an Account","5. Return to main menu"};
	
	public static void loginMenu() {
		ApprovedAccounts.customerLogin();
	}
	
	public static void actionMenu(Account a) {
		
		System.out.println("Welcome customer, what would you like to do?");
		
		for(int i =0; i<optionsArray.length;i++) {
			System.out.println(optionsArray[i]);
		}
		
		String choice = sc.nextLine();
		Handler.actionHandler(choice, a, "admin");
		
	}
}
