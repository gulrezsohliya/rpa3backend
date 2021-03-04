package rpa.dao.admin;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

/*import org.apache.log4j.Logger;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rpa.models.master.User;
import rpa.utility.UtilityInterface;

@Repository("InitializationDao")
@Transactional
public class InitializationDao implements InitializationDaoInterface {

	private static final Logger LOG = Logger.getLogger(InitializationDao.class.getName());
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired private UtilityInterface UI;
	
	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<User> listUsers() {

		List<User> list = null;
		try {
			String sql = "Select * From backend.userlogins Order by username";
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
		} catch (Exception ex) {
			LOG.info("\n\nError in listUsers " + ex);
		}
		return (list != null) ? list : new LinkedList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User listUsers(Integer usercode) {

		List<User> list = null;
		try {
			String sql = "Select * From backend.userlogins Where usercode=? Order by username";
			list = (List<User>) jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), usercode);
		} catch (Exception ex) {
			LOG.info("\n\nError in listUsers " + ex);
		}
		return (list != null) ? list.get(0) : new User();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User listUsers(String username) {

		User list = null;
		try {
			String sql = "Select * From backend.userlogins Where username=? Order by username";
			list = (User) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), "su");
		} catch (Exception ex) {
			LOG.info("\n\nError in listUsers " + ex);
		}
		return (list != null) ? list : new User();
	}

	/* CREATE DATA */
	@Override
	public boolean saveUser(User user) {
		String sql = "";
		boolean response = false;
		try {
			user.setUsercode(UI.getMax("backend", "userlogins", "usercode")+1);
			LOG.info(user.toString());
			sql = "INSERT INTO backend.userlogins(   " 
					+ "            cellcode, usercode, username, passwords, "
					+ " 			fullname, mobileno, designation, entrydate)   "
					+ "    VALUES (?, ?, ?, ?, ?, ?, ?, now())";
			
			Object[] param = new Object[] { user.getCellcode(), user.getUsercode(), user.getUsername(),
					user.getPasswords(), user.getFullname(), user.getMobileno(), user.getDesignation() };
			response = jdbcTemplate.update(sql, param) > 0;
			LOG.log(Level.FINE, response ? "Success" : "Failed");
		} catch (Exception e) {
			response = false;
			LOG.log(Level.SEVERE, e.toString());
		}
		return response;
	}
	
	@Override
	public boolean updateUser(User user) {
		LOG.info("DAO: updateUser");
		
		String sql = "";
		boolean response = false;
		try {
			sql = "UPDATE backend.userlogins SET  " 
					+ "            cellcode = ?, username = ?, passwords = ?, "
					+ " 		   fullname = ?, mobileno = ?, designation = ?   "
					+ "    WHERE usercode = ?";
			
			Object[] param = new Object[] { 
					user.getCellcode(), user.getUsername(), user.getPasswords(), 
					user.getFullname(), user.getMobileno(), user.getDesignation(), user.getUsercode() 
			};
			response = jdbcTemplate.update(sql, param) > 0;
			LOG.log(Level.FINE, response ? "Success" : "Failed");
		} catch (Exception e) {
			response = false;
			LOG.log(Level.SEVERE, e.toString());
		}
		return response;
	}
}
