package rpa.dao.admin;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import rpa.models.master.Pageurls;
import rpa.models.master.UserPages;
import rpa.utility.Utility;

@Repository("PageurlsDao")
@Transactional
public class PageurlsDao {

	@Autowired private Utility util;
	
	private ObjectMapper mapper = new ObjectMapper();
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void createTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
//    @Autowired
//    utilitycommon.UtilityCommonDao commonDao;

//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = HibernateException.class, readOnly = false)
//    public boolean savePageurlsDao(Pageurls url) {
//        boolean response = false;
//        Session session = sessionFactory.getCurrentSession();
//        try {
//            if (url.getUrlcode() != 0) {
//                session.update(url);
//            } else {
//                int max = commonDao.getMax("model.persitent.Pageurls", "urlcode");
//                url.setUrlcode(max);
//                session.save(url);
//            }
//            response = true;
//        } catch (Exception ex) {
//            System.out.println("Error in savePageurlsDao" + ex);
//        }
//        return response;
//    }

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Pageurls> getPageurls() {

		List<Map<String, Object>> list = null;
		List<Pageurls> urllist = null;
		try {
			String sql = "Select * From backend.Pageurls Order by parent, submenu, subsubmenu";
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Pageurls> getMappedPageurls(String username) {

		List<Pageurls> urllist = null;
		List<Map<String, Object>> rowList = null;
		try {
			String sql = "Select url.* From backend.userlogins u ,backend.UserPages up,backend.pageurls url "
					+ "WHERE u.usercode=up.usercode " + "and up.urlcode=url.urlcode " + "and username=:username "
					+ "order by url.parent, url.submenu, url.subsubmenu";
			SqlParameterSource parameters = new MapSqlParameterSource().addValue("username",username);
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

//
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Map<String, Object>> getHeaders() {
		List<Map<String, Object>> list = null;
		try {
			String sql = "Select DISTINCT parent ,parenticon " + "From backend.Pageurls Order by parent ";
			list = jdbcTemplate.queryForList(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getHeaders " + ex);
		}
		return (list != null) ? list : new LinkedList();
	}

	@Transactional
	public List<Map<String, Object>> getSubmenu(String parent) {
		List<Map<String, Object>> list = null;
		try {
			String sql = "Select Distinct submenu,submenuicon From backend.Pageurls Where parent=? Order by submenu ";
			list = jdbcTemplate.queryForList(sql, parent);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getSubmenu " + ex);
		}
		return (list != null) ? list : new LinkedList();
	}

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
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public boolean mapUserpages(List<UserPages> upage) {
        boolean response = false;
        try {
            String sql = "DELETE From backend.UserPages WHERE usercode=? ";
            if(jdbcTemplate.update(sql, upage.get(0).getUsercode())<0) {
            	return false;
            }
            /////////////////////////////////////
            sql="INSERT INTO backend.userpages(userpagecode, usercode, urlcode) VALUES (?, ?, ?)";
            int max=util.getMax("backend", "UserPages","userpagecode");
            for (UserPages up : upage) {
                up.setUserpagecode(++max);
                jdbcTemplate.update(sql, up.getUserpagecode(),up.getUsercode(),up.getUrl().getUrlcode());
            }
            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("\n\nError in mapUserpages " + ex);
        }
        return response;
    }
}
