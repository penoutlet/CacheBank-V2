package menus;


import java.util.List;
import java.util.Scanner;

import beans.Account;
import beans.User;
import dao.AccountDimple;
import dao.Transaction;
import dao.UserDimple;
import utilities.Handler;

public class AdminMenu {
	
	static Scanner sc = new Scanner(System.in);
	UserDimple udi = new UserDimple();
	AccountDimple adi = new AccountDimple();
	MainMenu mainMenu = new MainMenu();
	
	private static String[] optionsArray1 = {"1. Find an account.", "2. View all usernames.", "3. Return to prior menu (logout)."};
	private static String[] optionsArray2 = {"0. View account","1. Deposit", "2. Withdraw", "3. Transfer", "4. Cancel account (delete) ", "5. Return to admin menu."};
	public AdminMenu() {};
	
	// both admins and employees use this menu
	public void mainMenu() {
		UtilityMenus utilMenu = new UtilityMenus();
		System.out.println("Welcome Admin, what would you like to do?");
		
		for(int i =0; i<optionsArray1.length;i++) {
			System.out.println(optionsArray1[i]);
		}
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		
		utilMenu.firstInputHandler(choice);
	}
	
	//both customers and admins use this menu
	public void actionMenu(Account a) {
		
		for(int i =0; i<optionsArray2.length;i++) {
			System.out.println(optionsArray2[i]);
		}
		
		sc = new Scanner(System.in);
		String choice = sc.nextLine();
		Handler handle = new Handler();
		handle.actionHandler(choice, a);
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
		String foundUn = user.getUsername();
		String foundPw = user.getPassword();
		if(!foundUn.equals("admin")) { // if username is not "emp", it is not the employee.
			System.out.println(foundUn + " is not an admin.");
			mainMenu.mainMenu();
		}
		else if(!pw.equals(foundPw)) { // if username is "emp," but the password is wrong, then no go.
			System.out.println("Incorrect password.");
			mainMenu.mainMenu();
		}
		mainMenu();
	}
		
//	// New code for altering user information (unimplemented code)*********************************************
//	private static Account editMenu(Account a) {
//		sc = new Scanner(System.in);
//		System.out.println("1. Edit?");
//		System.out.println("2. Return");
//		String choice = sc.nextLine();
//		
//		switch (choice) {
//		case "1":
//			String changeMe = editWhich();
//			System.out.println("Enter new value.");
//			String changeTo = sc.nextLine();
//			ApprovedAccounts.edit(a, changeMe, changeTo);
//			
//			break;
//
//		default:
//			break;
//		}
//		return a;
//	}
//	
//	private static String editWhich() {
//		
//		System.out.println("1. Edit first name.");
//		System.out.println("2. Edit last name.");
//		System.out.println("3. Edit joint user's first name.");
//		System.out.println("4. Edit joint user's last name.");
//		sc = new Scanner(System.in);
//		String choice = sc.nextLine();
//		switch(choice) {
//		case "1":
//			return "firstname";
//		case "2":
//			return "lastname";
//		case "3":
//			return "jointFirst";
//		case "4":
//			return "jointLast";
//		default:
//			return "";
//		}
//	}
}
