package items;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1619123874811695284L;
	private String username;
	private transient String password;
	private double balance;
	private boolean isApproved;
	private String firstname;
	private String lastname;
	private String jointFirst;
	private String jointLast;
	
	

	@Override
	public String toString() {
		return "Account [username=" + username + ", balance=" + balance + ", isApproved="
				+ isApproved + ", firstname=" + firstname + ", lastname=" + lastname + ", jointFirst=" + jointFirst+ ", jointLast=" + jointLast + "]";
	}

	public Account(String username, String password, double balance, String firstname,
			String lastname, String jointFirst, String jointLast) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.firstname = firstname;
		this.lastname = lastname;
		this.jointFirst = jointFirst;
		this.jointLast = jointLast;
		this.isApproved = false;
	}

	public Account(String username, String password, double balance, String firstname, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isApproved = false;
	}
	
	public Account(String username, String password, double balance, String firstname, String lastname, boolean isApproved) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isApproved = isApproved;
	}
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
