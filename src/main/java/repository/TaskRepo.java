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

}
