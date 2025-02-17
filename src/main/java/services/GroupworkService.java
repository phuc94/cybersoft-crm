package services;

import java.util.List;

import crm08.entity.JobEntity;
import repository.GroupworkRepo;

public class GroupworkService {
	
	private GroupworkRepo groupworkRepo = new GroupworkRepo();
	
	public List<JobEntity> findAll() {
		
		return groupworkRepo.findAll();
		
	}
}
