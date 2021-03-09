package rpa.services.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpa.dao.admin.InitializationDaoInterface;
import rpa.models.master.Cell;
import rpa.models.master.ExamCenter;
import rpa.models.master.Office;
import rpa.models.master.User;
import rpa.models.master.Venue;
import rpa.utility.UtilityInterface;

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
	public List<Cell> listCells() {
		List<Cell> cells =null;
		try {
			String sql = "SELECT * FROM MASTERS.cells ORDER BY officecode,celldescription";
			cells = UI.listGeneric(Cell.class, sql);
			for(Cell c:cells) {
				sql = "SELECT * FROM MASTERS.offices where officecode=? ORDER BY officecode";
				c.setOffice((UI.listGeneric(Office.class, sql,new Object[]{c.getOfficecode()})).get(0));
			}
		}catch(Exception e) {
			LOG.info("Error in listCells() :: "+e);
		}
		return cells;
	}

	@Override
	public List<Cell> listCells(Integer officecode) {
		String sql = "SELECT * FROM MASTERS.cells WHERE officecode=? ORDER BY celldescription";
		List<Cell> cells = UI.listGeneric(Cell.class, sql, new Object[] {officecode});
		for(Cell c:cells) {
			sql = "SELECT * FROM MASTERS.offices where officecode=? ORDER BY officecode";
			c.setOffice((UI.listGeneric(Office.class, sql,new Object[]{officecode})).get(0));
		}
		return cells;
	}

	@Override
	public List<ExamCenter> listExamCenters() {
		String sql = "SELECT * FROM backend.examinationcenters order by centername";
		List<ExamCenter> examCenters = UI.listGeneric(ExamCenter.class, sql);
		return examCenters;
	}

	@Override
	public List<Venue> listVenues() {
		String sql = "SELECT * FROM backend.venues order by venuename";
		List<Venue> venues = UI.listGeneric(Venue.class, sql);
		for(Venue c:venues) {
			sql = "SELECT * FROM backend.examinationcenters where centercode=? ORDER BY centercode";
			c.setExamCenter((UI.listGeneric(ExamCenter.class, sql,new Object[]{c.getCentercode()})).get(0));
		}
		return venues;
	}

	@Override
	public List<Office> listOffices() {
		String sql = "SELECT  officecode, officename1, officename2, officename3, officeshortname, "
				+ "       signatoryname, signatorydesignation, emailid, emailidpassword, "
				+ "       smsusername, smspassword, smssenderid, enabled " 
				+ "FROM MASTERS.offices "
				+ "ORDER BY officename1, officename2, officename3";
		List<Office> offices = UI.listGeneric(Office.class, sql);
		return offices;
	}

	@Override
	public List<Office> listOffices(Integer officecode) {
		String sql = "SELECT  officecode, officename1, officename2, officename3, officeshortname, "
				+ "       signatoryname, signatorydesignation, emailid, emailidpassword, "
				+ "       smsusername, smspassword, smssenderid, enabled " + "FROM MASTERS.offices "
				+ "WHERE officecode = ? "
				+ "ORDER BY officename1, officename2, officename3";
		List<Office> offices = UI.listGeneric(Office.class, sql, new Object[] {officecode});
		return offices;
	}

	@Override
	public List<User> listUser() {
		String sql = "SELECT UL.cellcode, UL.usercode, UL.username,    " + 
				"	UL.fullname, UL.mobileno, UL.designation, UL.enabled,    " + 
				"	MC.celldescription,   " + 
				"	MO.officecode, MO.officename1, MO.officename2, " + 
				"	MO.officename3, MO.officeshortname    " + 
				"FROM backend.userlogins UL   " + 
				"INNER JOIN masters.cells MC ON MC.cellcode = UL.cellcode   " + 
				"INNER JOIN masters.offices MO ON MO.officecode = MC.officecode   " + 
				"ORDER BY UL.username";
		List<User> users = UI.listGeneric(User.class, sql);
		return users;
	}
	
	@Override
	public User listUser(Integer usercode) {
		String sql = "SELECT UL.cellcode, UL.usercode, UL.username,    " + 
				"	UL.fullname, UL.mobileno, UL.designation, UL.enabled,    " + 
				"	MC.celldescription,   " + 
				"	MO.officecode, MO.officename1, MO.officename2, " + 
				"	MO.officename3, MO.officeshortname    " + 
				"FROM backend.userlogins UL   " + 
				"INNER JOIN masters.cells MC ON MC.cellcode = UL.cellcode   " + 
				"INNER JOIN masters.offices MO ON MO.officecode = MC.officecode   " + 
				"WHERE UL.usercode = ?   " + 
				"ORDER BY UL.username";
		List<User> users = UI.listGeneric(User.class, sql, new Object[] {usercode});
		return users.get(0);
	}
	/* CREATE/UPDATE DATA */
	
	//USER
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
	
	//CELL
	@Override
	public String createcell(Cell cell) {
		
		String sql ="SELECT * FROM masters.cells WHERE celldescription =? and officecode=? ";
		if((UI.listGeneric(Cell.class, sql, new Object[] {cell.getCelldescription(),cell.getOfficecode()})).size()>0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.cells(cellcode, officecode,  celldescription, enabled ) VALUES (?, ?, ?, ?);";
		Object[] params=new Object[] {
				UI.getMax("masters", "cells", "cellcode")+1,
				cell.getOfficecode(),cell.getCelldescription(),"Y"};
		return (UI.update("master.cell", sql, params))?"CREATED":"FAILED";
	}
	
	@Override
	public boolean updatecell(Cell cell) {
		String sql = "UPDATE masters.cells SET officecode=?, celldescription=?, enabled=? "
				+ "WHERE  cellcode=? ";
		Object[] params=new Object[] {cell.getOfficecode(),cell.getCelldescription(),cell.getEnabled(),cell.getCellcode()};
		return UI.update("master.cells", sql, params);
	}
	
	@Override
	public boolean deletecell(Integer cellcode) {
		
		String sql ="Delete from masters.cells where cellcode=? ";
		return UI.update("master.cells", sql, new Object[] {cellcode});
	}
	
	//OFFICE
	@Override
	public String createOffice(Office office) {
		
		String sql ="SELECT * FROM masters.offices WHERE officename1 =? and officename2 =? and officename3 =? ";
		if((UI.listGeneric(Office.class, sql, new Object[] {office.getOfficename1(),office.getOfficename2(),office.getOfficename3()})).size()>0) {
			return "EXISTS";
		}
		sql = "INSERT INTO masters.offices(officecode, officename1, officename2, officename3, officeshortname,  " + 
				"            signatoryname, signatorydesignation, emailid, emailidpassword,  " + 
				"            smsusername, smspassword, smssenderid, enabled) " + 
				"    VALUES (?, ?, ?, ?, ?,  " + 
				"            ?, ?, ?, ?,  " + 
				"            ?, ?, ?, ?)";
		Object[] params=new Object[] {
				UI.getMax("masters", "offices", "officecode")+1,
				office.getOfficename1(), office.getOfficename2(), office.getOfficename3(), office.getOfficeshortname(), 
				office.getSignatoryname(), office.getSignatorydesignation(), office.getEmailid(), office.getEmailidpassword(),  
				office.getSmsusername(), office.getSmspassword(), office.getSmssenderid(),"Y"};
		return (UI.update("master.cell", sql, params))?"CREATED":"FAILED";
	}
	
	@Override
	public boolean updateOffice(Office office) {
		String sql = "UPDATE masters.offices SET officename1=?, officename2=?, officename3=?, officeshortname=?,  " + 
				"            signatoryname=?, signatorydesignation=?, emailid=?, emailidpassword=?,  " + 
				"            smsusername=?, smspassword=?, smssenderid=?, enabled=? " 
				+ "WHERE  officecode=? ";
		Object[] params=new Object[] {office.getOfficename1(), office.getOfficename2(), office.getOfficename3(), office.getOfficeshortname(), 
				office.getSignatoryname(), office.getSignatorydesignation(), office.getEmailid(), office.getEmailidpassword(),  
				office.getSmsusername(), office.getSmspassword(), office.getSmssenderid(),office.getEnabled(),
				office.getOfficecode()};
		return UI.update("master.cells", sql, params);
	}
	
	//EXAMCENTER
	@Override
	public String createExamCenter(ExamCenter center) {
		
		String sql ="SELECT * FROM backend.examinationcenters WHERE centername =? ";
		if((UI.listGeneric(Office.class, sql, new Object[] {center.getCentername()})).size()>0) {
			return "EXISTS";
		}
		sql = "INSERT INTO backend.examinationcenters(centercode, centername)VALUES (?, ?)";
		Object[] params=new Object[] {
				UI.getMax("backend", "examinationcenters", "centercode")+1,
				center.getCentername()};
		return (UI.update("backend.examinationcenters", sql, params))?"CREATED":"FAILED";
	}
	
	@Override
	public boolean updateExamCenter(ExamCenter center) {
		String sql = "UPDATE backend.examinationcenters SET centername=? WHERE  centercode=? ";
		Object[] params=new Object[] {center.getCentername(),center.getCentercode()};
		return UI.update("master.cells", sql, params);
	}

	@Override
	public boolean deleteExamCenter(Integer centercode) {
		
		String sql ="Delete from backend.examinationcenters where centercode=? ";
		return UI.update("backend.examinationcenters", sql, new Object[] {centercode});
	}

	//Venue
	@Override
	public String createVenue(Venue venue) {
		
		String sql ="SELECT * FROM backend.venues WHERE venuename =? ";
		if((UI.listGeneric(Venue.class, sql, new Object[] {venue.getVenuename()})).size()>0) {
			return "EXISTS";
		}
		sql = "INSERT INTO backend.venues(venuecode,centercode, venuename)VALUES (?, ?, ?)";
		Object[] params=new Object[] {
				UI.getMax("backend", "venues", "venuecode")+1,
				venue.getCentercode(),
				venue.getVenuename()};
		return (UI.update("backend.venues", sql, params))?"CREATED":"FAILED";
	}
	
	@Override
	public boolean updateVenue(Venue venue) {
		String sql = "UPDATE backend.venues SET centercode=?, venuename=? WHERE  venuecode=? ";
		Object[] params=new Object[] {venue.getCentercode(),venue.getVenuename(),venue.getVenuecode()};
		return UI.update("venue.venues", sql, params);
	}
	
	@Override
	public boolean deleteVenue(Integer venuecode) {
		
		String sql ="Delete from backend.venues where venuecode=? ";
		return UI.update("backend.venues", sql, new Object[] {venuecode});
	}
	
}
