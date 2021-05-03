package rpa.Services.Admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.print.attribute.standard.MediaSize.Other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpa.Dao.Admin.InitializationDaoInterface;
import rpa.Models.Examination.Advertisement;
import rpa.Models.Examination.ExamCenter;
import rpa.Models.Examination.ExamSubjects;
import rpa.Models.Examination.OfficeCenter;
import rpa.Models.Examination.OptionalSubjects;
import rpa.Models.Examination.Venue;
import rpa.Models.master.Categories;
import rpa.Models.master.Cell;
import rpa.Models.master.Office;
import rpa.Models.master.OtherCategories;
import rpa.Models.master.User;
import rpa.Models.master.UserPages;
import rpa.Utility.UtilityInterface;

@Service("IntializationService")
public class IntializationService implements IntializationServiceInterface {

	private static final Logger LOG = Logger.getLogger(IntializationService.class.getName());

	@Autowired
	private InitializationDaoInterface ID;
	@Autowired
	private UtilityInterface UI;

	/*
	 * @Override public User listUser(Integer usercode) { return
	 * ID.listUsers(usercode); }
	 */

	@Override
	public User listUser(String username) {
		return ID.listUsers(username);
	}

	/* List */
	@Override
	public List<Categories> listCategories() {
		List<Categories> oth = null;

		String sql = "SELECT * FROM masters.categories where enabled='Y' order by categorycode ";

		oth = UI.listGeneric(Categories.class, sql);
		return oth;
	}

	@Override
	public List<OtherCategories> listOtherCategories() {
		List<OtherCategories> oth = null;

		String sql = "SELECT * FROM MASTERS.othercategories ORDER BY othercategorydescription";
		oth = UI.listGeneric(OtherCategories.class, sql);
		return oth;
	}

	@Override
	public List<Cell> listCells() {
		List<Cell> cells = null;

		String sql = "SELECT * FROM MASTERS.cells ORDER BY officecode,celldescription";
		cells = UI.listGeneric(Cell.class, sql);
		for (Cell c : cells) {
			sql = "SELECT * FROM MASTERS.offices where officecode=? ORDER BY officecode";
			c.setOffice((UI.listGeneric(Office.class, sql, new Object[] { c.getOfficecode() })).get(0));
		}
		return cells;
	}

	@Override
	public List<Cell> listCellsforOffice(Integer officecode) {
		String sql = "SELECT * FROM MASTERS.cells WHERE officecode=? ORDER BY celldescription";
		List<Cell> cells = UI.listGeneric(Cell.class, sql, new Object[] { officecode });
		for (Cell c : cells) {
			sql = "SELECT * FROM MASTERS.offices where officecode=? ORDER BY officecode";
			c.setOffice((UI.listGeneric(Office.class, sql, new Object[] { officecode })).get(0));
		}
		return cells;
	}

	@Override
	public List<Cell> listCellsforCode(Integer cellcode) {
		String sql = "SELECT * FROM MASTERS.cells WHERE cellcode=? ORDER BY celldescription";
		List<Cell> cells = UI.listGeneric(Cell.class, sql, new Object[] { cellcode });
		for (Cell c : cells) {
			sql = "SELECT * FROM MASTERS.offices where officecode=? ORDER BY officecode";
			c.setOffice((UI.listGeneric(Office.class, sql, new Object[] { c.getOfficecode() })).get(0));
		}
		return cells;
	}

	@Override
	public List<ExamCenter> listExamCenters() {
		String sql = "SELECT * FROM masters.examinationcenters e INNER JOIN masters.officecenters o on o.centercode=e.centercode order by centername";
		List<ExamCenter> examCenters = UI.listGeneric(ExamCenter.class, sql);
		return examCenters;
	}

	@Override
	public List<ExamCenter> listExamCenters(Integer officecode) {
		String sql = "SELECT * FROM masters.examinationcenters e INNER JOIN masters.officecenters o on o.centercode=e.centercode WHERE o.officecode=? "
				+ " Order by centername";
		List<ExamCenter> examCenters = UI.listGeneric(ExamCenter.class, sql, new Object[] { officecode });
		return examCenters;
	}

	@Override
	public List<Venue> listVenues() {
		String sql = "SELECT * FROM masters.venues v "
				+ "INNER JOIN masters.examinationcenters e on e.centercode=v.centercode "
				+ "INNER JOIN masters.officecenters o on o.centercode=e.centercode ORDER by venuename";
		List<Venue> venues = UI.listGeneric(Venue.class, sql);
		for (Venue c : venues) {
			sql = "SELECT * FROM masters.examinationcenters where centercode=? ORDER BY centercode";
			c.setExamCenter((UI.listGeneric(ExamCenter.class, sql, new Object[] { c.getCentercode() })).get(0));
		}
		return venues;
	}

	@Override
	public List<Venue> listVenues(Integer officecode) {
		String sql = "SELECT * FROM masters.venues v "
				+ "INNER JOIN masters.examinationcenters e on e.centercode=v.centercode "
				+ "INNER JOIN masters.officecenters o on o.centercode=e.centercode  "
				+ "WHERE officecode=? Order by venuename";
		List<Venue> venues = UI.listGeneric(Venue.class, sql, new Object[] { officecode });
		for (Venue c : venues) {
			sql = "SELECT * FROM masters.examinationcenters where centercode=? ORDER BY centercode";
			c.setExamCenter((UI.listGeneric(ExamCenter.class, sql, new Object[] { c.getCentercode() })).get(0));
		}
		return venues;
	}

	@Override
	public List<Office> listOffices() {
		String sql = "SELECT  officecode, officename1, officename2, officename3, officeshortname, "
				+ "       signatoryname, signatorydesignation, emailid, emailidpassword, "
				+ "       smsusername, smspassword, smssenderid, enabled FROM MASTERS.offices "
				+ "ORDER BY officename1, officename2, officename3";
		List<Office> offices = UI.listGeneric(Office.class, sql);
		return offices;
	}

	@Override
	public List<Office> listOffices(Integer officecode) {
		String sql = "SELECT  officecode, officename1, officename2, officename3, officeshortname, "
				+ "       signatoryname, signatorydesignation, emailid, emailidpassword, "
				+ "       smsusername, smspassword, smssenderid, enabled FROM MASTERS.offices "
				+ "WHERE officecode = ? ORDER BY officename1, officename2, officename3";
		List<Office> offices = UI.listGeneric(Office.class, sql, new Object[] { officecode });
		return offices;
	}

	@Override
	public List<Office> listOfficesAndMappedCenters() {

		List<Office> response = new ArrayList<Office>();
		List<Office> offices = listOffices();
		String sql = "SELECT e.* FROM masters.examinationcenters e "
				+ "INNER JOIN masters.officecenters oc on oc.centercode=e.centercode " + "WHERE oc.officecode=? "
				+ "order by centername";
		List<ExamCenter> centers;
		for (Office o : offices) {
			centers = UI.listGeneric(ExamCenter.class, sql, new Object[] { o.getOfficecode() });
			o.setCenters(centers);
			response.add(o);
		}
		return response;
	}

	@Override
	public List<Office> listOfficesAndMappedCenters(Integer officecode) {

		List<Office> offices = listOffices(officecode);
		String sql = "SELECT e.* FROM masters.examinationcenters e "
				+ "INNER JOIN masters.officecenters oc on oc.centercode=e.centercode " + "WHERE oc.officecode=? "
				+ "order by centername";
		List<ExamCenter> centers = UI.listGeneric(ExamCenter.class, sql, new Object[] { officecode });
		offices.get(0).setCenters(centers);
		return offices;
	}

	@Override
	public List<User> listUser() {
		String sql = "SELECT UL.cellcode, UL.usercode, UL.username,    "
				+ "	UL.fullname, UL.mobileno, UL.designation, UL.enabled,    MC.celldescription,   "
				+ "	MO.officecode, MO.officename1, MO.officename2, MO.officename3, MO.officeshortname    "
				+ "FROM backend.userlogins UL   INNER JOIN masters.cells MC ON MC.cellcode = UL.cellcode   "
				+ "INNER JOIN masters.offices MO ON MO.officecode = MC.officecode   ORDER BY UL.username";
		List<User> users = UI.listGeneric(User.class, sql);
		return users;
	}

	@Override
	public User listUser(Integer usercode) {
		String sql = "SELECT UL.cellcode, UL.usercode, UL.username,    "
				+ "	UL.fullname, UL.mobileno, UL.designation, UL.enabled,    " + "	MC.celldescription,   "
				+ "	MO.officecode, MO.officename1, MO.officename2, " + "	MO.officename3, MO.officeshortname    "
				+ "FROM backend.userlogins UL   " + "INNER JOIN masters.cells MC ON MC.cellcode = UL.cellcode   "
				+ "INNER JOIN masters.offices MO ON MO.officecode = MC.officecode   " + "WHERE UL.usercode = ?   "
				+ "ORDER BY UL.username";
		List<User> users = UI.listGeneric(User.class, sql, new Object[] { usercode });
		return (!users.isEmpty()) ? users.get(0) : new User();
	}
	/* CREATE/UPDATE DATA */

	// USER
	@Override
	public boolean saveUser(User user) {
		return ID.saveUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		LOG.info("updateUser");
		return ID.updateUser(user);
	}

	@Override
	public boolean updateUserStatus(User user) {
		LOG.info("updateUser");
		return ID.updateUserStatus(user);
	}

	// CELL
	@Override
	public String createcell(Cell cell) {

		String sql = "SELECT * FROM masters.cells WHERE celldescription =? and officecode=? ";
		if ((UI.listGeneric(Cell.class, sql, new Object[] { cell.getCelldescription(), cell.getOfficecode() }))
				.size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.cells(cellcode, officecode,  celldescription, enabled ) VALUES (?, ?, ?, ?);";
		Object[] params = new Object[] { UI.getMax("masters", "cells", "cellcode") + 1, cell.getOfficecode(),
				cell.getCelldescription(), "Y" };
		return (UI.update("master.cell", sql, params)) ? "CREATED" : "FAILED";
	}

	@Override
	public boolean updatecell(Cell cell) {
		String sql = "UPDATE masters.cells SET officecode=?, celldescription=?, enabled=? " + "WHERE  cellcode=? ";
		Object[] params = new Object[] { cell.getOfficecode(), cell.getCelldescription(), cell.getEnabled(),
				cell.getCellcode() };
		return UI.update("master.cells", sql, params);
	}

	@Override
	public boolean deletecell(Integer cellcode) {

		String sql = "Delete from masters.cells where cellcode=? ";
		return UI.update("master.cells", sql, new Object[] { cellcode });
	}

	// OFFICE
	@Override
	public String createOffice(Office office) {

		String sql = "SELECT * FROM masters.offices WHERE officename1 =? and officename2 =? and officename3 =? ";
		if ((UI.listGeneric(Office.class, sql,
				new Object[] { office.getOfficename1(), office.getOfficename2(), office.getOfficename3() }))
						.size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.offices(officecode, officename1, officename2, officename3, officeshortname,  "
				+ "            signatoryname, signatorydesignation, emailid, emailidpassword,  "
				+ "            smsusername, smspassword, smssenderid, enabled) " + "    VALUES (?, ?, ?, ?, ?,  "
				+ "            ?, ?, ?, ?,  " + "            ?, ?, ?, ?)";
		Object[] params = new Object[] { UI.getMax("masters", "offices", "officecode") + 1, office.getOfficename1(),
				office.getOfficename2(), office.getOfficename3(), office.getOfficeshortname(),
				office.getSignatoryname(), office.getSignatorydesignation(), office.getEmailid(),
				office.getEmailidpassword(), office.getSmsusername(), office.getSmspassword(), office.getSmssenderid(),
				"Y" };
		return (UI.update("master.cell", sql, params)) ? "CREATED" : "FAILED";
	}

	@Override
	public boolean updateOffice(Office office) {
		String sql = "UPDATE masters.offices SET officename1=?, officename2=?, officename3=?, officeshortname=?,  "
				+ "            signatoryname=?, signatorydesignation=?, emailid=?, emailidpassword=?,  "
				+ "            smsusername=?, smspassword=?, smssenderid=?, enabled=? " + "WHERE  officecode=? ";
		Object[] params = new Object[] { office.getOfficename1(), office.getOfficename2(), office.getOfficename3(),
				office.getOfficeshortname(), office.getSignatoryname(), office.getSignatorydesignation(),
				office.getEmailid(), office.getEmailidpassword(), office.getSmsusername(), office.getSmspassword(),
				office.getSmssenderid(), office.getEnabled(), office.getOfficecode() };
		return UI.update("master.cells", sql, params);
	}

	// EXAMCENTER
	@Override
	public String createExamCenter(ExamCenter center) {

		String sql = "SELECT * FROM masters.examinationcenters WHERE centername =? ";
		if ((UI.listGeneric(Office.class, sql, new Object[] { center.getCentername() })).size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.examinationcenters(centercode, centername)VALUES (?, ?)";
		int max = UI.getMax("masters", "examinationcenters", "centercode");
		Object[] params = new Object[] { max + 1, center.getCentername() };
		if (UI.update("masters.examinationcenters", sql, params)) {
			sql = "INSERT INTO masters.officecenters(officecode, centercode) VALUES (?, ?)";
			return (UI.update("masters.officecenters", sql, new Object[] { center.getOfficecode(), max + 1 }))
					? "CREATED"
					: "FAILED";
		} else {
			return "FAILED";
		}
	}

	@Override
	public boolean updateExamCenter(ExamCenter center) {
		String sql = "UPDATE masters.examinationcenters SET centername=? WHERE  centercode=? ";
		Object[] params = new Object[] { center.getCentername(), center.getCentercode() };
		return UI.update("master.cells", sql, params);
	}

	@Override
	public boolean deleteExamCenter(Integer centercode) {

		String sql = "Delete from masters.examinationcenters where centercode=? ";
		return UI.update("masters.examinationcenters", sql, new Object[] { centercode });
	}

	// Venue
	@Override
	public String createVenue(Venue venue) {

		String sql = "SELECT * FROM masters.venues WHERE venuename =? ";
		if ((UI.listGeneric(Venue.class, sql, new Object[] { venue.getVenuename() })).size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.venues(venuecode,centercode, venuename)VALUES (?, ?, ?)";
		Object[] params = new Object[] { UI.getMax("masters", "venues", "venuecode") + 1, venue.getCentercode(),
				venue.getVenuename() };
		return (UI.update("masters.venues", sql, params)) ? "CREATED" : "FAILED";
	}

	@Override
	public boolean updateVenue(Venue venue) {
		String sql = "UPDATE masters.venues SET centercode=?, venuename=? WHERE  venuecode=? ";
		Object[] params = new Object[] { venue.getCentercode(), venue.getVenuename(), venue.getVenuecode() };
		return UI.update("venue.venues", sql, params);
	}

	@Override
	public boolean deleteVenue(Integer venuecode) {

		String sql = "Delete from masters.venues where venuecode=? ";
		return UI.update("masters.venues", sql, new Object[] { venuecode });
	}

	// OtherCategory
	@Override
	public String createOtherCategory(OtherCategories categories) {

		String sql = "SELECT * FROM masters.othercategories WHERE othercategorydescription =? ";
		if ((UI.listGeneric(OtherCategories.class, sql, new Object[] { categories.getOthercategorydescription() }))
				.size() > 0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.othercategories(othercategorycode,othercategorydescription)VALUES ( ?, ?)";
		Object[] params = new Object[] { UI.getMax("masters", "othercategories", "othercategorycode") + 1,
				categories.getOthercategorydescription() };
		return (UI.update("masters.othercategories", sql, params)) ? "CREATED" : "FAILED";
	}

	@Override
	public boolean updateOtherCategory(OtherCategories categories) {
		String sql = "UPDATE masters.othercategories SET othercategorydescription=? WHERE  othercategorycode=? ";
		Object[] params = new Object[] { categories.getOthercategorydescription(), categories.getOthercategorycode() };
		return UI.update("masters.othercategories", sql, params);
	}

	@Override
	public boolean deleteOtherCategory(Integer othercategorycode) {

		String sql = "Delete from masters.othercategories where othercategorycode=? ";
		return UI.update("masters.othercategories", sql, new Object[] { othercategorycode });
	}

	@Override
	public boolean saveOfficeCenters(List<OfficeCenter> officeCenter) {
		System.out.println(officeCenter);
		String sql = "DELETE From masters.officecenters WHERE officecode=? ";
		if (!UI.update("masters.officecenters", sql, new Object[] { officeCenter.get(0).getOfficecode() })) {
			System.out.println("DELETE = " + officeCenter.get(0).getOfficecode());
			return false;
		}
		/////////////////////////////////////
		sql = "INSERT INTO masters.officecenters(officecode, centercode) VALUES (?, ?)";
//		int max = UI.getMax("masters", "officecenters", "slno");
		for (OfficeCenter oc : officeCenter) {
			if (!UI.update("masters.OfficeCenter", sql, new Object[] { oc.getOfficecode(), oc.getCentercode() })) {
				return false;
			}
		}
		return true;
	}

}
