package Users;
import items.Account;

public class Customer {
	
	Account account;
	String customerID;
	boolean isApproved=false;
	
	
	
	
	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getCustomerID() {
		return this.customerID;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
}
