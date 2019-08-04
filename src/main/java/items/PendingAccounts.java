package items;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Menus.MainMenu;

public class PendingAccounts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7812411237134521524L;
	static HashMap<String, Account> pendingAccounts = new HashMap<String, Account>();

	// methods for pending accounts hashmap

	public static void addAll(ArrayList<Account> data) {

		for (Account a : data) {
			if (a == null) {
				MainMenu.firstLoginMenu();
			}
			pendingAccounts.put(a.getUsername(), a);
		}

	}

	public static void deserialize() throws FileNotFoundException {
		ArrayList<Account> accounts = new ArrayList<Account>();
		accounts.add((Account) Persistence.readData("./pendingaccounts.txt"));
//		System.out.println("Deserialization successful from pending.");
		addAll(accounts);
	}

	public static void serializeAll() {
		ArrayList<Account> accounts = fetchAll();
		Persistence.writeList(accounts, "pendingaccounts");
	}

	public static void addOne(String username, Account account) {
		if (fetchOne(username) == null) {
			pendingAccounts.put(username, account);
			serializeAll();
		} else {
			System.out.println("Username already in use.");
		}
	}

	public static ArrayList<Account> fetchAll() {
		ArrayList<Account> pendingList = new ArrayList<Account>();
		Set<String> keys = pendingAccounts.keySet();
		for (String key : keys) {
			Account a = pendingAccounts.get(key);
			pendingList.add(a);
		}

		return pendingList;
	}

	public static Account fetchOne(String username) {
		Set<String> keys = pendingAccounts.keySet();
		Account account = null;
		for (String u : keys) {
			if (username.equals(u)) {
				account = pendingAccounts.get(u);
				System.out.println("Fetched");

			}
		}
		return account;

	}

	public static void removeOne(String username) {
		Set<String> keys = pendingAccounts.keySet();
		Account a = fetchOne(username);
		if (a != null) {

			pendingAccounts.remove(a.getUsername());
			serializeAll();
		} else {
			System.out.println("Account not found.");
			MainMenu.mainMenu();
		}
	}
}
