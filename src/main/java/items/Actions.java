package items;

import org.apache.log4j.Logger;

public class Actions {
//	private HashMap<String, Account> loggedInUsers = new HashMap<String, Account>();
	private static Logger log = Logger.getLogger(Transaction.class);
	
//	public static void login(Account a)
	public static Transaction deposit(Account a, double amount) {
		Transaction t = new Transaction("deposit", amount, a);
		execute(t,a,null);
		log.info(a.getUsername() + " deposited " + amount);
		return t;
	}
	public static void withdraw(Account a, double amount) {
		Transaction t = new Transaction("withdraw", amount,a);
		execute(t,a,null);
		log.info(a.getUsername() + " withdrew " + amount);
	}
	
	public static Account transfer(Account a, double amount, Account transferTo) {
		Transaction t = new Transaction("transfer", amount, a);
		execute(t,a,transferTo);
		log.info(a.getUsername() + " transferred " + amount + " to " + transferTo.getUsername());
		return a;
	}
	
	public static void cancel(Account a) {
		ApprovedAccounts.removeOne(a.getUsername());
	}
	public static Account execute(Transaction t, Account a, Account transferTo) {
		double currBalance = a.getBalance();
		double amount = t.getAmount();
		if(t.getAction().equals("deposit")){
			double newBal = currBalance += amount;
			a.setBalance(newBal);
			ApprovedAccounts.updateOne(a);
//			return a;
		}
		else if(t.getAction().equals("withdraw")) {
			if(t.getAmount()>currBalance) {
				System.out.println("Insufficient funds.");
				return a;
			}
			
			double newBal = currBalance -= amount;
			a.setBalance(newBal);
			ApprovedAccounts.updateOne(a);
//			return a;
			
		}
		else if(t.getAction().equals("transfer")) {
			if(t.getAmount()>currBalance) {
				System.out.println("Insufficient funds.");
				return a;
			}
			
			a.setBalance(currBalance -= amount);
			ApprovedAccounts.updateOne(a);
			double newTransferBal = transferTo.getBalance() + amount;
			transferTo.setBalance(newTransferBal);
			ApprovedAccounts.updateOne(transferTo);
//			return a;
		}
		return a;
	}
}
