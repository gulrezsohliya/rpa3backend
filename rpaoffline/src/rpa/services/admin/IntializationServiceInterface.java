package rpa.services.admin;

import java.util.List;

import rpa.models.master.Cell;
import rpa.models.master.Office;
import rpa.models.master.User;

public interface IntializationServiceInterface {
	/* READ DATA */

	public User listUser(Integer usercode);

	public User listUser(String username);

	/* List */

	public List<Cell> listCells();

	public List<Cell> listCells(Integer officecode);

	public List<Office> listOffices();
	
	public List<Office> listOffices(Integer officecode);

	public List<User> listUser();

	/* CREATE DATA */
	public boolean saveUser(User user);

	public boolean updateUser(User user);

}
