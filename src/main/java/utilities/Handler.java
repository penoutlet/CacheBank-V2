package utilities;

import beans.Account;
import menus.AdminMenu;
import menus.CustomerMenu;
import menus.MainMenu;
import menus.UtilityMenus;

public class Handler {
//	public void actionHandler(String choice, Account a) {
//		boolean flag = true;
//		MainMenu mainMenu = new MainMenu();
//		AdminMenu adminMenu = new AdminMenu();
//		CustomerMenu custMenu = new CustomerMenu();
//		UtilityMenus utilMenu = new UtilityMenus();
//
//		// String returnMenu = "admin";
//		while (flag) {
//
//			switch (choice) {
//			case "0":
//				System.out.println("AID: " + a.getAID() + ", balance: " + a.getBalance() );
//				adminMenu.actionMenu(a);
//
//				flag = !flag;
//				break;
//			case "1":
//				utilMenu.depositMenu(a);
//				flag = !flag;
//				break;
//			case "2":
//				utilMenu.withdrawMenu(a);
//				flag = !flag;
//				break;
//			case "3":
//				utilMenu.transferMenu(a);
//				flag = !flag;
//				break;
//			case "4":
//				utilMenu.cancelMenu(a);
//				flag = !flag;
//				break;
//			case "5":
//				flag = !flag;
//				break;
//			default:
//				System.out.println("Select an option.");
//				flag = !flag;
//				break;
//			}
//		}
//	}
	
	public void actionHandler(String choice, Account a) {
		boolean flag = true;
		MainMenu mainMenu = new MainMenu();
		AdminMenu adminMenu = new AdminMenu();
		CustomerMenu custMenu = new CustomerMenu();
		UtilityMenus utilMenu = new UtilityMenus();
		// String returnMenu = "admin";
		while (flag) {

			switch (choice) {
			case "0":
				System.out
				.println("AID: " + a.getAID() + ", balance: " + a.getBalance());
	adminMenu.actionMenu(a);

				flag = !flag;
				break;
			case "1":
				utilMenu.depositMenu(a);
				flag = !flag;
				break;
			case "2":
				utilMenu.withdrawMenu(a);
				flag = !flag;
				break;
			case "3":
				utilMenu.transferMenu(a);
				flag = !flag;
				break;
			case "4":
				utilMenu.cancelMenu(a);
				flag = !flag;
				break;
			case "5":
				adminMenu.mainMenu();
				flag = !flag;
				break;
			default:
				System.out.println("Select an option.");
				flag = !flag;
				break;
			}
		}
	}
}
