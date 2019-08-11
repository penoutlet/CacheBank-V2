import static org.junit.Assert.assertEquals;

import org.junit.Test;

import beans.Account;
import beans.User;
import dao.Transaction;

public class JUnitTests {
	// truncate all the tables and re-compile the sequence before running this file.

	@Test
	public void findUserByUsername() {
		Transaction t = new Transaction();
		User u = new User("JUnitUser", "pw");
		t.createUser(u);
		assertEquals("Found user?", "JUnitUser", t.findUser(u.getUsername()).getUsername());
	}

	@Test
	public void findAIDByToken() {
		Transaction t = new Transaction();
		Account a = new Account(null, 1337.0, 0, "JUnitToken");
		t.createAccount(a);
		assertEquals("Found AID?", "1", t.findAIDByToken("JUnitToken"));

	}

	@Test
	public void findAccountByAID() {
		Transaction t = new Transaction();
		Account a = new Account(null, 1338.0, 0, "JUnitToken2");
		t.createAccount(a);
		String aid = t.findAIDByToken("JUnitToken2");
		assertEquals("Found account?", aid, t.findAccountByAID(aid).getAID());

	}

	@Test
	public void findAIDByUsername() {
		User u = new User("JUnitUser2", "pw");
		Transaction t = new Transaction();
		Account a = new Account(null, 1338.0, 0, "JUnitToken3");
		t.createAccount(a);
		String aid = t.findAIDByToken("JUnitToken3");
		t.insertIntoJunction(u.getUsername(), aid);
		assertEquals("Account found?", aid, t.findAIDByUsername(u.getUsername()));

	}

}
