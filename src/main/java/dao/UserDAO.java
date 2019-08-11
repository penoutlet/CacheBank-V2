package dao;
import java.util.List;

import beans.User;

public interface UserDAO {
	public void insertUser(User u);
	public User findUserByUsername(String username);
	public List<String> findAllUsernames();
	public void updateUserPassword(User u);
	public void deleteUser(User u);
}
