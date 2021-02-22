package rpa.services.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpa.dao.admin.AdminDao;
import rpa.models.master.User;

@Service("AdminService")
public class AdminService {

	@Autowired
	private AdminDao dao;

	public List<User> listUser() {

		return dao.listUsers();
	}

	public User listUser(Integer usercode) {

		return dao.listUsers(usercode);
	}

	public User listUser(String username) {

		return dao.listUsers(username);
	}
}
