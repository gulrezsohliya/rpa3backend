package rpa.utility;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class Utility {
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
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
}
