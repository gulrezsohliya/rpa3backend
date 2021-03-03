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
	private InitializationDaoInterface dao;
	@Autowired
	private UtilityInterface UI;

	@Override
	public User listUser(Integer usercode) {
		return dao.listUsers(usercode);
	}

	@Override
	public User listUser(String username) {
		return dao.listUsers(username);
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
		String sql = "SELECT * FROM backend.userlogins ORDER BY username";
		List<User> users = UI.listGeneric(User.class, sql);
		return users;
	}

}
