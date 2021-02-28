package rpa.services.admin;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import rpa.models.master.Pageurls;
import rpa.models.master.User;
import rpa.models.master.UserPages;

public interface PageurlsServiceInterface {

	public JSONArray getPageurls(String username);

	public List<Pageurls> listUrls();

	public String getHeaders();

	public String savePageurl(Pageurls url);

	public List<Map<String, Object>> getSubmenu(String parent);

	public List<User> listUserAndMappedPages();

	public String saveUserpages(List<UserPages> upages);

}
