package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm08.config.MysqlConfig;
import crm08.entity.JobEntity;
import crm08.entity.StatusEntity;
import crm08.entity.TaskEntity;
import crm08.entity.UserEntity;

public class TaskRepo {
	
	public List<TaskEntity> findAll() {
		Connection conn = MysqlConfig.getConnection();
		String query = "SELECT t.id, t.name as task_name, u.fullname as user_fullname, t.start_date, t.end_date,  j.name as job_name, s.name as status_name\r\n"
				+ "FROM tasks t\r\n"
				+ "JOIN status s on t.status_id = s.id \r\n"
				+ "JOIN users u on t.user_id = u.id\r\n"
				+ "JOIN jobs j on t.job_id = j.id;";
		List<TaskEntity> taskList = new ArrayList<TaskEntity>();

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TaskEntity task = new TaskEntity();
				JobEntity job = new JobEntity();
				UserEntity user = new UserEntity();
				StatusEntity status = new StatusEntity();
				job.setName(result.getString("job_name"));
				user.setFullName(result.getString("user_fullname"));
				status.setName(result.getString("status_name"));

				task.setId(result.getInt("id"));
				task.setName(result.getString("task_name"));
				task.setStart_date(result.getString("start_date"));
				task.setEnd_date(result.getString("end_date"));

				task.setJob(job);
				task.setUser(user);
				task.setStatus(status);

				taskList.add(task);
			}

		} catch (SQLException e) {
			System.out.println("TaskRepo:findAll: " + e.getLocalizedMessage());
		}
		
		return taskList;
		
	}
	
	public int saveTask(String name, String start_date, String end_date, String user_id, String job_id) {
		Connection conn = MysqlConfig.getConnection();
		int result = 0;
		String query = "INSERT INTO tasks (name, start_date, end_date, user_id, job_id, status_id)\r\n"
				+ "VALUES (?, ?, ?, ?, ?, '1')";
		
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, start_date);
			statement.setString(3, end_date);
			statement.setString(4, user_id);
			statement.setString(5, job_id);
			result = statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("TaskRepo:findAll: " + e.getLocalizedMessage());
		}
		return result;
	}

}
