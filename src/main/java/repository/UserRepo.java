package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import crm08.config.MysqlConfig;
import crm08.entity.RoleEntity;
import crm08.entity.UserEntity;

public class UserRepo {


	public List<UserEntity> findUserByEmailAndPassword(String email, String password) {

		String query = "SELECT u.id, u.email, u.password, u.fullname, u.avatar, r.name as role_name, r.description as role_description\r\n"
				+ "FROM users u\r\n"
				+ "JOIN roles r ON r.id=u.role_id\r\n"
				+ "WHERE u.email = ? AND u.password = ?";
		List<UserEntity> userList = new ArrayList<UserEntity>();
		
		try { 
			Connection conn = MysqlConfig.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet results = statement.executeQuery();

			while(results.next()) {
				RoleEntity userRole = new RoleEntity();
				UserEntity user = new UserEntity(); 
				userRole.setName(results.getString("role_name"));
				userRole.setDescription(results.getString("role_description"));

				user.setRole(userRole);
				user.setId(results.getInt("id"));
				user.setEmail(results.getString("email"));
				user.setFullName(results.getString("fullname"));
				user.setAvatar(results.getString("avatar"));
				user.setPassword(results.getString("password"));
				userList.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("UserRepo:findUserByEmailAndPassword: " + e.getLocalizedMessage());
		}

		return userList;
	}

	public List<UserEntity> findAll() {
		List<UserEntity> userList = new ArrayList<UserEntity>();
		String query = "SELECT * FROM users";
		
		try {
			Connection conn = MysqlConfig.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				UserEntity user = new UserEntity();
				user.setId(results.getInt("id"));
				user.setEmail(results.getString("email"));
				user.setFullName(results.getString("fullname"));
				user.setAvatar(results.getString("avatar"));
				user.setPassword(results.getString("password"));
				userList.add(user);
			}
			
		} catch (SQLException e) {
			System.out.println("UserRepo:findAll: " + e.getLocalizedMessage());
		}
		
		return userList;
	}
	
	public int saveUser(String email, String password, String fullname, String avatar, String role_id) {
		String query = "INSERT INTO users\r\n"
				+ "(email, password, fullname, avatar, role_id)\r\n"
				+ "VALUES (?, ?, ?, ?, ?)";
		int result = 0;

		try {
			Connection conn = MysqlConfig.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			statement.setString(3, fullname);
			statement.setString(4, avatar);
			statement.setString(5, role_id);
			result = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("UserRepo:saveUser: " + e.getLocalizedMessage());
		}
		
		return result;
	}
	
	public int deleteUser(int id) {
		String query = "DELETE FROM users WHERE id = ?";
		int result = 0;
		
		try {
			Connection conn = MysqlConfig.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("UserRepo:deleteUser: " + e.getLocalizedMessage());
		}
		return result;
	}
	
}



