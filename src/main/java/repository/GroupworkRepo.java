package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm08.config.MysqlConfig;
import crm08.entity.JobEntity;

public class GroupworkRepo {
	
	public List<JobEntity> findAll() {
		Connection conn = MysqlConfig.getConnection();
		List<JobEntity> jobList = new ArrayList<JobEntity>();
		String query = "SELECT * FROM jobs";
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				JobEntity job = new JobEntity();
				job.setId(results.getInt("id"));
				job.setStart_date(results.getString("start_date"));
				job.setEnd_date(results.getString("end_date"));
				job.setName(results.getString("name"));
				jobList.add(job);
			}
			
		} catch (SQLException e) {
			System.out.println("GroupworkRepo:findAll: " + e.getLocalizedMessage());
		}
		
		return jobList;
		
	}

}
