package rpa.services.admin;

import java.util.List;

import rpa.models.master.Cell;
import rpa.models.master.ExamCenter;
import rpa.models.master.Office;
import rpa.models.master.User;
import rpa.models.master.Venue;

public interface IntializationServiceInterface {
	/* READ DATA */

	public User listUser(Integer usercode);

	public User listUser(String username);

	/* List */
	public List<Cell> listCells();

	public List<Cell> listCells(Integer officecode);

	public List<ExamCenter> listExamCenters();
	
	public List<Venue> listVenues();

	public List<Office> listOffices();

	public List<Office> listOffices(Integer officecode);

	public List<User> listUser();

	/* CREATE/UPDATE DATA */
	public boolean updateUserStatus(User user);
	
	public boolean saveUser(User user);
	public boolean updateUser(User user);

	public String createcell(Cell cell);
	public boolean updatecell(Cell cell);
	public boolean deletecell(Integer cellcode);

	public String createOffice(Office office);
	public boolean updateOffice(Office office);
	
	public String createExamCenter(ExamCenter center);
	public boolean updateExamCenter(ExamCenter center); 
	public boolean deleteExamCenter(Integer centercode); 

	public String createVenue(Venue venue);
	public boolean updateVenue(Venue venue);
	public boolean deleteVenue(Integer venuecode);
	
}
