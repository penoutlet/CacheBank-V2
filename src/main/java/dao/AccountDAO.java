package dao;
import java.util.List;

import beans.Account;

public interface AccountDAO {
	public void insertAccount(Account a);
	public Account findAccountByAID(String aid);
	public List<Account> findAllAccounts();
	public void updateAccountBalance(Account a);
	public void updateAccountApproval(Account a);
	public void deleteAccount(Account a);
}
