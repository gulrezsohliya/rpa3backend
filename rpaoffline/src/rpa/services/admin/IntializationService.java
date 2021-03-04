package rpa.services.admin;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpa.dao.admin.InitializationDaoInterface;
import rpa.models.master.Cell;
import rpa.models.master.Office;
import rpa.models.master.User;
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
		LOG.info("SERIVCE listCells");
		String sql = "SELECT * FROM MASTERS.cells ORDER BY celldescription";
		List<Cell> cells = UI.listGeneric(Cell.class, sql);
		LOG.info(cells.toString());
		return cells;
	}

	@Override
	public List<Cell> listCells(Integer officecode) {
		LOG.info("SERIVCE listCells");
		String sql = "SELECT * FROM MASTERS.cells WHERE officecode=? ORDER BY celldescription";
		List<Cell> cells = UI.listGeneric(Cell.class, sql, new Object[] {officecode});
		LOG.info(cells.toString());
		return cells;
	}

	@Override
	public List<Office> listOffices() {
		String sql = "SELECT  officecode, officename1, officename2, officename3, officeshortname, "
				+ "       signatoryname, signatorydesignation, emailid, emailidpassword, "
				+ "       smsusername, smspassword, smssenderid, enabled " + "FROM MASTERS.offices "
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
	/* CREATE DATA */
	@Override
	public boolean saveUser(User user) {
		LOG.info("saveUser");
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
}
