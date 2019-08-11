package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;

public class UserDimple implements UserDAO {
	static Connection conn;
	private static String url = "jdbc:oracle:thin:@samjavadb.cecxuvq3cw6r.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "sambo";
	private static String password = "sambo";
	
	@Override
	public void insertUser(User u) {
		// TODO Auto-generated method stub
		try(Connection conn = DriverManager.getConnection(url, username,password)){
			PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public User findUserByUsername(String un) {
		// TODO Auto-generated method stub
		User u = null;
		try(Connection conn = DriverManager.getConnection(url,username,password)){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username =?");
			ps.setString(1, un);
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				u = new User(rs.getString("username"),rs.getString("password"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public List<String> findAllUsernames() {
		// TODO Auto-generated method stub
		List<String> users = new ArrayList<String>();
		try(Connection conn = DriverManager.getConnection(url,username,password)){
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
			ps.executeQuery();
			ResultSet rs = ps.getResultSet();
			while(rs.next()) {
				users.add(rs.getString("username"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void updateUserPassword(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}
	
}
