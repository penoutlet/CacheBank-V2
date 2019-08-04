package utilities;

import items.Account;
import items.ApprovedAccounts;
import items.PendingAccounts;

public class Dummies {
	// add test peoples
		public static void addDummies() {
			ApprovedAccounts.addOne("testa", new Account("testa", "testa", 10000, "testa", "testa",true));
			ApprovedAccounts.addOne("testb", new Account("testb", "testb", 10000, "testb", "testb",true));
			ApprovedAccounts.addOne("testc", new Account("testc", "testc", 10000, "testc", "testc",true));
			ApprovedAccounts.addOne("testd", new Account("testd", "testd", 10000, "testd", "testd",true));
			PendingAccounts.addOne("testp", new Account("testp", "testp", 10000, "testp", "testp"));
		}
}
