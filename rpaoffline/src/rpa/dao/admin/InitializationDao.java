package rpa.dao.admin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rpa.models.master.User;

@Repository("InitializationDao")
@Transactional
public class InitializationDao implements InitializationDaoInterface {

	private static final Logger LOG = Logger.getLogger(InitializationDao.class);
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
			list = (List<User>) jdbcTemplate.queryForList(sql, User.class, usercode);
		} catch (Exception ex) {
			LOG.info("\n\nError in listUsers " + ex);
		}
		return (list != null) ? list.get(0) : new User();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User listUsers(String username) {

		List<User> list = null;
		try {
			String sql = "Select * From backend.userlogins Where username=? Order by username";
			list = (List<User>) jdbcTemplate.queryForList(sql, User.class, username);
		} catch (Exception ex) {
			LOG.info("\n\nError in listUsers " + ex);
		}
		return (list != null) ? list.get(0) : new User();
	}

	
}
