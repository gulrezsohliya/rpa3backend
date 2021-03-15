package rpa.Dao.Admin;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import rpa.Models.master.Pageurls;
import rpa.Models.master.UserPages;
import rpa.Utility.UtilityInterface;

@Repository("PageurlsDao")
@Transactional
public class PageurlsDao implements PageurlsDaoInterface {

	@Autowired
	private UtilityInterface util;

	private ObjectMapper mapper = new ObjectMapper();
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean savePageurlsDao(Pageurls url) {
		boolean response = false;
		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("pageurl", url.getPageurl())
					.addValue("subsubmenu", url.getSubsubmenu()).addValue("subsubmenuicon", url.getSubsubmenuicon())
					.addValue("submenu", url.getSubmenu()).addValue("submenuicon", url.getSubmenuicon())
					.addValue("parent", url.getParent()).addValue("parenticon", url.getParenticon())
					.addValue("urlcode", url.getUrlcode())
					.addValue("parentorder", url.getParentorder())
					.addValue("submenuorder", url.getSubmenuorder())
					.addValue("subsubmenuorder", url.getSubsubmenuorder())
					;

			if (url.getUrlcode() != 0) {
				String sql = (new StringBuilder("UPDATE backend.pageurls "))
						.append("   SET pageurl=:pageurl, subsubmenu=:subsubmenu, subsubmenuicon=:subsubmenuicon,  ")
						.append("   submenu=:submenu, submenuicon=:submenuicon, parent=:parent, parenticon=:parenticon,  ")
						.append("   subsubmenuorder=:subsubmenuorder, submenuorder=:submenuorder, parentorder=:parentorder  ")
						.append(" WHERE urlcode=:urlcode ").toString();
				if (namedParameterJdbcTemplate.update(sql, parameters) < 0) {
					return false;
				}
			} else {
				int max = util.getMax("backend", "pageurls", "urlcode");
				url.setUrlcode(max+1);
				parameters.addValue("urlcode", url.getUrlcode());
				String sql = (new StringBuilder("INSERT INTO backend.pageurls("))
						.append("urlcode, pageurl, subsubmenu, subsubmenuicon, ")
						.append("submenu, submenuicon, parent, parenticon,")
						.append("subsubmenuorder, submenuorder, parentorder)")
						.append("VALUES (:urlcode, :pageurl, :subsubmenu, :subsubmenuicon, ")
						.append(":submenu, :submenuicon, :parent, :parenticon, ")
						.append(":subsubmenuorder,:submenuorder, :parentorder)").toString();
				if (namedParameterJdbcTemplate.update(sql, parameters) < 0) {
					return false;
				}
			}
			response = true;
		} catch (Exception ex) {
			System.out.println("Error in savePageurlsDao ::" + ex);
		}
		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Pageurls> getPageurls() {

		List<Map<String, Object>> list = null;
		List<Pageurls> urllist = null;
		try {
			String sql = "Select * From backend.Pageurls ORDER BY parentorder,submenuorder,subsubmenu";
			list = (List<Map<String, Object>>) jdbcTemplate.queryForList(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getPageurls " + ex);
		}
		if (list != null) {
			urllist = new LinkedList<Pageurls>();
			for (Map<String, Object> row : list) {
				urllist.add(mapper.convertValue(row, Pageurls.class));
			}
		}
		return (urllist != null) ? urllist : new LinkedList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Pageurls> getMappedPageurls(String username) {

		List<Pageurls> urllist = null;
		List<Map<String, Object>> rowList = null;
		try {
			String sql = "Select url.* From backend.userlogins u ,backend.UserPages up,backend.pageurls url "
					+ "WHERE u.usercode=up.usercode " + "and up.urlcode=url.urlcode " + "and username=:username "
					+ "ORDER BY parentorder,submenuorder,subsubmenuorder";
			SqlParameterSource parameters = new MapSqlParameterSource().addValue("username", username);
			rowList = (List<Map<String, Object>>) namedParameterJdbcTemplate.queryForList(sql, parameters);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getPageurls " + ex);
		}
		if (rowList != null) {
			urllist = new LinkedList<Pageurls>();
			for (Map<String, Object> row : rowList) {
				urllist.add(mapper.convertValue(row, Pageurls.class));
			}
		}
		return (urllist != null) ? urllist : new LinkedList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Map<String, Object>> getHeaders() {
		List<Map<String, Object>> list = null;
		try {
			String sql = "Select DISTINCT ON (parent) parent ,parenticon " + "From backend.Pageurls Order by parent ";
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getHeaders " + ex);
		}
		return (list != null) ? list : new LinkedList();
	}

	@Override
	@Transactional
	public List<Map<String, Object>> getSubmenu(String parent) {
		List<Map<String, Object>> list = null;
		try {
			String sql = "Select Distinct ON (submenu) submenu,submenuicon From backend.Pageurls Where parent=? Order by submenu ";
			list = jdbcTemplate.queryForList(sql, parent);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getSubmenu " + ex);
		}
		return (list != null) ? list : new LinkedList();
	}

	@Override
	@Transactional
	public List<Map<String, Object>> getSubsubmenu(String parent, String submenu) {
		List<Map<String, Object>> list = null;
		try {
			String sql = "Select Distinct subsubmenu,subsubmenuicon From backend.Pageurls Where parent=? AND submenu=? Order by subsubmenu ";
			list = jdbcTemplate.queryForList(sql, parent, submenu);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getSubsubmenu " + ex);
		}
		return (list != null) ? list : new LinkedList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean mapUserpages(List<UserPages> upage) {
		boolean response = false;
		try {
			String sql = "DELETE From backend.UserPages WHERE usercode=? ";
			if (jdbcTemplate.update(sql, upage.get(0).getUsercode()) < 0) {
				return false;
			}
			/////////////////////////////////////
			sql = "INSERT INTO backend.userpages(userpagecode, usercode, urlcode) VALUES (?, ?, ?)";
			int max = util.getMax("backend", "UserPages", "userpagecode");
			for (UserPages up : upage) {
				up.setUserpagecode(++max);
				jdbcTemplate.update(sql, up.getUserpagecode(), up.getUsercode(), up.getUrl().getUrlcode());
			}
			response = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in mapUserpages " + ex);
		}
		return response;
	}
}
