package utilities;

import Menus.AdminMenu;
import Menus.CustomerMenu;
import Menus.MainMenu;
import items.Account;

public class Handler {
	public static void actionHandler(String choice, Account a, String returnMenu) {
		boolean flag = true;
		// String returnMenu = "admin";
		while (flag) {

			switch (choice) {
			case "0":
				System.out.println(a.toString());
				if (returnMenu.equals("admin")) {
					AdminMenu.actionMenu(a);

				} else if (returnMenu.equals("customer")) {
					CustomerMenu.actionMenu(a);
				}
				flag = !flag;
				break;
			case "1":
				// TestUtility.depositMenu(a,returnMenu);
				UtilityMenus.depositMenu(a, returnMenu);
				flag = !flag;
				break;
			case "2":
				UtilityMenus.withdrawMenu(a, returnMenu);
				flag = !flag;
				break;
			case "3":
				UtilityMenus.transferMenu(a, returnMenu);
				flag = !flag;
				break;
			case "4":
				UtilityMenus.cancelMenu(a, returnMenu);
				flag = !flag;
				break;
			case "5":
				if (returnMenu.equals("admin")) {
					AdminMenu.mainMenu();

				} else if (returnMenu.equals("customer")) {
					MainMenu.mainMenu();
				}
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
