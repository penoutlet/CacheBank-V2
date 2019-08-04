package Users;

import org.apache.log4j.Logger;

import Menus.EmployeeMenu;
import items.Account;
import items.ApprovedAccounts;
import items.PendingAccounts;
public class Employee {
	private final String username = "Sam";
	private final String password = "1";
	private static Logger log = Logger.getLogger(Employee.class);
	
	
	public String getUsername() {
		return username;
	}



	public String getPassword() {
		return password;
	}



	public static void approve(Account account){
			try { account.setApproved(true);
			ApprovedAccounts.addOne(account.getUsername(), account);
			PendingAccounts.removeOne(account.getUsername());
			System.out.println("Application approved.");
			log.info(account.getUsername() + " was approved.");
			}
			catch (NullPointerException e) {
				e.printStackTrace();
				System.out.println("Unable to approve a null account xP");
			}
			
			EmployeeMenu.actionMenu();
	}
	public static void deny(Account account){
		PendingAccounts.removeOne(account.getUsername());
		System.out.println("Application denied, user removed from pending accounts.");
		log.info(account.getUsername() + " was denied.");
}
	}
