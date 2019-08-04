package items;


public class Transaction {
	public String action = "";
	public double amount;
	private Account a;
	//write a method that takes in an action as a string as one parameter, and an amount as the second, that handles the following cases:
		// depositing
		/*
		 * withdrawing
		 * transferring
		 * canceling - takes in no amount - this is a privilege that should belong to admin and customers only and not transactions
		 * Transaction.sendTransaction(t);
		 */
	
	public Transaction(String action, double amount, Account a) {
		// TODO Auto-generated constructor stub
		this.action = action;
		this.amount = amount;
		this.a = a;
	}
	
	
	@Override
	public String toString() {
		return "Transaction [action=" + action + ", amount=" + amount + "]";
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	
}
