package services;

import repository.RoleRepo;

public class RoleService {
	
	private RoleRepo roleRepo = new RoleRepo();
	
	public boolean saveRole(String name, String description) {
		if (roleRepo.saveRole(name, description) == 1) {
			return true;
		} else {
			return false;
		}
	}

}
