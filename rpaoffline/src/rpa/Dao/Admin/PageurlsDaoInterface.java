package rpa.Dao.Admin;

import java.util.List;
import java.util.Map;

import rpa.Models.master.Pageurls;
import rpa.Models.master.UserPages;

public interface PageurlsDaoInterface {

	public boolean mapUserpages(List<UserPages> upages);

	public List<Map<String, Object>> getSubsubmenu(String parent, String submenu);

	public List<Map<String, Object>> getHeaders();

	public List<Map<String, Object>> getSubmenu(String parent);

	public List<Pageurls> getMappedPageurls(Integer usercode);

	public List<Pageurls> getPageurls();

	public boolean savePageurlsDao(Pageurls url);

}
