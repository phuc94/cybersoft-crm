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

	public List<UserEntity> getAllUsers() {
		List<UserEntity> userList = userRepo.findAll();
		if (userList.size() > 0) {
			return userList;
		} else {
			return new ArrayList<UserEntity>();
		}
	}
	
	public boolean saveUser(String email, String password, String fullname, String avatar, String role_id) {
		if (userRepo.saveUser(email, password, fullname, avatar, role_id) == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteUser(int id) {
		if (userRepo.deleteUser(id) == 1) {
			return true;
		} else {
			return false;
		}
	}
}
