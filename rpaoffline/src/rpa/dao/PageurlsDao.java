package rpa.dao;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import rpa.models.Pageurls;
import rpa.models.UserPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("PageurlsDao")
@Transactional
public class PageurlsDao {

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

		String currentPrincipalName = (SecurityContextHolder.getContext().getAuthentication()).getName();
		List<Pageurls> list = null;
		try {
			String sql = "Select * From Pageurls "+
					"Order by parent, submenu, subsubmenu";
			list =(List<Pageurls>)jdbcTemplate.queryForList(sql, Pageurls.class) ;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getPageurls " + ex);
		} 
		return (list != null) ? list : new LinkedList();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Pageurls> getMappedPageurls() {

		List<Pageurls> urllist = null;
		List<UserPages> userurllist = null;
		try {
			String sql = "Select * From backend.UserPages " + "WHERE user.username=:username "
					+ "order by url.parent, url.submenu, url.subsubmenu";
			SqlParameterSource parameters = new MapSqlParameterSource().addValue("username",
					SecurityContextHolder.getContext().getAuthentication().getName());
			userurllist = (List<UserPages>) namedParameterJdbcTemplate.queryForList(sql, parameters, UserPages.class);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("\n\nError in getPageurls " + ex);
		}
		if (userurllist != null) {
			urllist = new LinkedList<Pageurls>();
			for (UserPages up : userurllist) {
				urllist.add(up.getUrl());
			}
		}
		return (urllist != null) ? urllist : new LinkedList();
	}
//
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = HibernateException.class, readOnly = true)
//    public List<Object[]> getHeaders() {
//        List<Object[]> list = null;
//        Session session = sessionFactory.getCurrentSession();
//        try {
//            String hql = "Select DISTINCT parent ,parenticon "
//                    + "From Pageurls Order by parent ";
//            Query query = session.createQuery(hql);
//            list = query.list();
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//            System.out.println("\n\nError in getHeaders " + ex);
//        } finally {
//            session.flush();
//        }
//        return (list != null) ? list : new LinkedList();
//    }
//
//    @Transactional
//    public List<Object[]> getSubmenu(String par) {
//        List<Object[]> list = null;
//        Session session = sessionFactory.getCurrentSession();
//        try {
//            String hql = "select Distinct submenu,submenuicon "
//                    + "From Pageurls "
//                    + "Where parent=:val "
//                    + "Order by submenu ";
//            Query query = session.createQuery(hql);
//            query.setParameter("val", par);
//            list = query.list();
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//            System.out.println("\n\nError in getSubmenu " + ex);
//        } finally {
//            session.flush();
//        }
//        return (list != null) ? list : new LinkedList();
//    }
//
//    @Transactional
//    public List<Object[]> getSubsubmenu(String parent, String submenu) {
//        List<Object[]> list = null;
//        Session session = sessionFactory.getCurrentSession();
//        try {
//            String hql = "select Distinct subsubmenu,subsubmenuicon "
//                    + "From Pageurls "
//                    + "Where parent=:parent "
//                    + "AND submenu=:submenu "
//                    + "Order by subsubmenu ";
//            Query query = session.createQuery(hql);
//            query.setParameter("parent", parent);
//            query.setParameter("submenu", submenu);
//            list = query.list();
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//            System.out.println("\n\nError in getSubsubmenu " + ex);
//        } finally {
//            session.flush();
//        }
//        return (list != null) ? list : new LinkedList();
//    }
}
