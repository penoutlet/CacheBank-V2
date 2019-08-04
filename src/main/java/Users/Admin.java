package Users;


import Menus.AdminMenu;
import items.Account;
import items.ApprovedAccounts;
import items.PendingAccounts;

public class Admin extends Employee {
	private final String username="Sam";
	private final String password="1";

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean deposit() {
		System.out.println("Deposit inside of admin");
		return true;
	};
	public static boolean withdraw() {
		System.out.println("withdraw inside of admin");
		return true;
	};
	public static  boolean transfer() {
		System.out.println("transfer inside of admin");
		return true;
	};
	public static boolean cancelAccount() {
		System.out.println("cancel inside of admin");
		return true;
	}

	public  String getUsername() {
		return username;
	}

	public  String getPassword() {
		return password;
	}

	
	public static void approve(Account account){
		try { account.setApproved(true);
		ApprovedAccounts.addOne(account.getUsername(), account);
		PendingAccounts.removeOne(account.getUsername());
		System.out.println("Application approved.");
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("Unable to approve a null account xP");
		}
		
		AdminMenu.actionMenu(account);
}
public static void deny(Account account){
	PendingAccounts.removeOne(account.getUsername());
	System.out.println("Application denied -- redirecting");
	AdminMenu.mainMenu();
}
	
	
}
