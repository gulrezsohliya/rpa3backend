package rpa.Services.Admin;

import java.util.List;

import rpa.Models.Examination.ExamCenter;
import rpa.Models.Examination.OfficeCenter;
import rpa.Models.Examination.Venue;
import rpa.Models.master.Categories;
import rpa.Models.master.Cell;
import rpa.Models.master.Office;
import rpa.Models.master.OtherCategories;
import rpa.Models.master.User;

public interface IntializationServiceInterface {
	/* READ DATA */

	public User listUser(Integer usercode);

	public User listUser(String username);

	/* List */
	public List<Categories> listCategories();

	public List<OtherCategories> listOtherCategories();

	public List<Cell> listCells();

	public List<Cell> listCellsforOffice(Integer officecode);

	public List<Cell> listCellsforCode(Integer cellcode);

	public List<ExamCenter> listExamCenters();

	public List<ExamCenter> listExamCenters(Integer officecode);

	public List<Venue> listVenues();

	public List<Venue> listVenues(Integer officecode);

	public List<Office> listOfficesAndMappedCenters();

	public List<Office> listOfficesAndMappedCenters(Integer officecode);

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

	public boolean saveOfficeCenters(List<OfficeCenter> officeCenter);

	public String createExamCenter(ExamCenter center);

	public boolean updateExamCenter(ExamCenter center);

	public boolean deleteExamCenter(Integer centercode);

	public String createVenue(Venue venue);

	public boolean updateVenue(Venue venue);

	public boolean deleteVenue(Integer venuecode);

	public String createOtherCategory(OtherCategories categories);

	public boolean updateOtherCategory(OtherCategories categories);

	public boolean deleteOtherCategory(Integer othercategorycode);

}
