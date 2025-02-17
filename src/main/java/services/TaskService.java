package services;

import java.util.List;

import crm08.entity.TaskEntity;
import repository.TaskRepo;

public class TaskService {
	private TaskRepo taskRepo = new TaskRepo();
	
	public List<TaskEntity> findAll() {
		return taskRepo.findAll();
	}

}
