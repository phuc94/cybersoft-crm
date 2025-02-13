package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm08.config.MysqlConfig;
import crm08.entity.RoleEntity;

public class RoleRepo {
	
	public List<RoleEntity> findAll() {

		String query = "SELECT * FROM roles";
		List<RoleEntity> roleList = new ArrayList<RoleEntity>();

		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				RoleEntity role = new RoleEntity();
				role.setId(result.getInt("id"));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
				roleList.add(role);
			}

		} catch (SQLException e) {
			System.out.println("RoleRepo:findAll: " + e.getLocalizedMessage());
		}

		return roleList;
		
	}

}
