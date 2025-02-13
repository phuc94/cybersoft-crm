package services;

import java.util.ArrayList;
import java.util.List;

import crm08.entity.RoleEntity;
import crm08.entity.UserEntity;
import repository.RoleRepo;
import repository.UserRepo;

public class UserService {
	
	private RoleRepo roleRepo = new RoleRepo();
	private UserRepo userRepo = new UserRepo();
	
	public List<RoleEntity> getRoles() {
		return roleRepo.findAll();
	}

	public List<UserEntity> authenticateUser(String email, String password) {
		List<UserEntity> userList = userRepo.findUserByEmailAndPassword(email, password);
		if (userList.size() > 0) {
			return userList;
		} else {
			return new ArrayList<UserEntity>();
		}
	}

}
