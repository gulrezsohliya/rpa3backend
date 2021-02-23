package rpa.dao.admin;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import rpa.controller.rest.admin.InitializationController;
import rpa.models.master.Pageurls;
import rpa.models.master.User;
import rpa.models.master.UserPages;

@Repository("AdminDao")
@Transactional
public class AdminDao {

	private static final Logger LOG = Logger.getLogger(AdminDao.class);
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<User> listUsers() {

		List<User> list = null;
		try {
			String sql = "Select * From backend.userlogins Order by username";
			list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class));
		} catch (Exception ex) {
			LOG.info("\n\nError in listUsers " + ex);
		}
		return (list != null) ? list : new LinkedList();
	}

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
