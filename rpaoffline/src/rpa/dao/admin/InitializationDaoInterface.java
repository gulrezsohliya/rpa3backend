package rpa.dao.admin;

import java.util.List;

import rpa.models.master.Cell;
import rpa.models.master.User;

public interface InitializationDaoInterface {

	/* READ DATA */
	public User listUsers(String username);

	public User listUsers(Integer usercode);

	/* List */
	public List<User> listUsers();

	/* CREATE DATA */
	public boolean saveUser(User user);

	public boolean updateUser(User user);
	
	
}
