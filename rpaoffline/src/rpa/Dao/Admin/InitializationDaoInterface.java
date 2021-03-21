package rpa.Dao.Admin;

import java.util.List;

import rpa.Models.Examination.ExamCenter;
import rpa.Models.master.Cell;
import rpa.Models.master.User;

public interface InitializationDaoInterface {

	/* READ DATA */
	public User listUsers(String username);

	public User listUsers(Integer usercode);

	/* List */
	public List<User> listUsers();

	/* CREATE DATA */
	public boolean saveUser(User user);

	public boolean saveExamCenter(ExamCenter examCenter);

	public boolean updateExamCenter(ExamCenter examCenter);

	public boolean updateUser(User user);

	public boolean updateUserStatus(User user);

}
