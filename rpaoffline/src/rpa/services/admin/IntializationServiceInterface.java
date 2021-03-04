package rpa.services.admin;

import java.util.List;

import rpa.models.master.Cell;
import rpa.models.master.ExamCenter;
import rpa.models.master.Office;
import rpa.models.master.User;

public interface IntializationServiceInterface {
	/* READ DATA */

	public User listUser(Integer usercode);

	public User listUser(String username);

	/* List */

	public List<Cell> listCells();

	public List<Cell> listCells(Integer officecode);

	public List<ExamCenter> listExamCenters();

	public List<Office> listOffices();

	public List<Office> listOffices(Integer officecode);

	public List<User> listUser();

	/* CREATE DATA */
	public boolean saveExamCenter(ExamCenter examCenter);
	
	public boolean saveUser(User user);

	public boolean updateExamCenter(ExamCenter examCenter);
	
	public boolean updateUser(User user);

	public boolean updateUserStatus(User user);

}
