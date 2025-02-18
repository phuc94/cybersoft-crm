package services;

import java.util.List;

import crm08.entity.JobEntity;
import repository.GroupworkRepo;

public class GroupworkService {
	
	private GroupworkRepo groupworkRepo = new GroupworkRepo();
	
	public List<JobEntity> findAll() {
		
		return groupworkRepo.findAll();
		
	}

	public boolean saveRole(String name, String startDate, String endDate) {
		if (groupworkRepo.saveGroupwork(name, startDate, endDate) == 1) {
			return true;
		} else {
			return false;
		}
	}

}
