package rpa.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class Utility implements UtilityInterface{
	
	private static final Logger LOG = Logger.getLogger(UtilityInterface.class);
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Integer getMax(String schema, String table,String column) {
		List<Map<String, Object>> list = null;
		Integer i=0;
		try {
			String sql = (new StringBuilder("Select max(")).append(column)
			.append(") From ")
			.append(schema).append(".").append(table).toString();
			 i= jdbcTemplate.queryForObject(sql,Integer.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getMax " + ex);
		}
		System.out.println("max::"+i);
		return (i!=null) ? i : 0;
	}
	
	@Override
	public <T> List<T> listGeneric(Class<T> clazz, String sql) {
		List<T> list = new ArrayList<>();
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz));
			LOG.info("Object: " + list.get(0).toString());
		} catch (Exception ex) {
			LOG.info("\n\nError in listGeneric " + ex);
		}
		return list;
	}

	@Override
	public <T> List<T> listGeneric(Class<T> clazz, String sql, Object[] params) {
		List<T> list = new ArrayList<>();
		try {
			list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(clazz), params);
			LOG.info("Object: " + list.get(0).toString());
		} catch (Exception ex) {
			LOG.info("\n\nError in listGeneric " + ex);
		}
		return list;
	}
}
