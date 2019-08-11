package beans;

public class Account {
	private String AID;
	private Double balance;
	private 	Integer isApproved;
	private Integer userType;
	private String tempToken;
	
	
	public Account(String aID, Double balance, Integer isApproved, String tempToken) {
		super();
		AID = aID;
		this.balance = balance;
		this.isApproved = isApproved;
		this.tempToken = tempToken;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}
	
	public Integer getIsApproved() {
		return isApproved;
	}
	public String getAID() {
		return AID;
	}
	public void setAID(String aID) {
		AID = aID;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Account(Double balance, Integer isApproved) {
		super();
		this.AID = null;
		this.balance = balance;
		this.isApproved = isApproved;
	}
	public String getTempToken() {
		return tempToken;
	}
	public void setTempToken(String tempToken) {
		this.tempToken = tempToken;
	}
	
	
}
