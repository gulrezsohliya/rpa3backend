package rpa.Services.Admin;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import rpa.Models.master.Pageurls;
import rpa.Models.master.Urls;
import rpa.Models.master.User;
import rpa.Models.master.UserPages;

public interface PageurlsServiceInterface {

	public List<Urls> getPageurls(Integer usercode);

	public List<Pageurls> listUrls();

	public String getHeaders();

	public String savePageurl(Pageurls url);

	public List<Map<String, Object>> getSubmenu(String parent);

	public List<User> listUserAndMappedPages();

	public String saveUserpages(List<UserPages> upages);

}
