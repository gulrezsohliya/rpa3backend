package rpa.dao.admin;

import java.util.List;
import java.util.Map;

import rpa.models.master.Pageurls;
import rpa.models.master.UserPages;

public interface PageurlsDaoInterface {

	public boolean mapUserpages(List<UserPages> upages);

	public List<Map<String, Object>> getSubsubmenu(String parent, String submenu);

	public List<Map<String, Object>> getHeaders();

	public List<Map<String, Object>> getSubmenu(String parent);

	public List<Pageurls> getMappedPageurls(String name);

	public List<Pageurls> getPageurls();

}
