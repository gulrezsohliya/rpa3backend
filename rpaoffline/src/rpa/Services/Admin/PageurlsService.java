/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpa.Services.Admin;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rpa.Dao.Admin.InitializationDaoInterface;
import rpa.Dao.Admin.PageurlsDaoInterface;
import rpa.Models.master.Pageurls;
import rpa.Models.master.User;
import rpa.Models.master.UserPages;

@Service("PageurlsService")
public class PageurlsService implements PageurlsServiceInterface {

	@Autowired
	PageurlsDaoInterface dao;
	@Autowired
	InitializationDaoInterface admindao;

	public JSONArray getPageurls(String username) {

		JSONArray arrParent = new JSONArray();
		JSONObject objParent = null;
		JSONObject objSubmenu = null;
		JSONObject objSubsubmenu = null;
		JSONArray arrSubmenu = null;
		JSONArray arrSubSubmenu = null;
		List<String> parent = new LinkedList<String>();
		List<String> submenu = new LinkedList<String>();
		for (Pageurls url : dao.getMappedPageurls(username)) {
			if (!parent.contains(url.getParent())) {
				parent.add(url.getParent());
				submenu = new LinkedList<String>();
				if (objSubmenu != null) {
					objSubmenu.put("value", (arrSubSubmenu != null) ? arrSubSubmenu : "");
					arrSubmenu.add(objSubmenu);
				}
				if (objParent != null) {
					objParent.put("value", arrSubmenu);
					arrParent.add(objParent);
				}
				objParent = new JSONObject();
				objParent.put("parent", url.getParent());
				objParent.put("parenticon", (url.getParenticon() != null) ? url.getParenticon() : "");
				objParent.put("pageurl", (url.getSubmenu() == null) ? url.getPageurl() : "");

				arrSubmenu = new JSONArray();
				objSubmenu = new JSONObject();
				submenu.add(url.getSubmenu());
				objSubmenu.put("submenu", url.getSubmenu());
				objSubmenu.put("submenuicon", (url.getSubmenuicon() != null) ? url.getSubmenuicon() : "");
				objSubmenu.put("pageurl", (url.getSubsubmenu() == null) ? url.getPageurl() : "");
				//
				arrSubSubmenu = new JSONArray();
				objSubsubmenu = new JSONObject();
				objSubsubmenu.put("subsubmenu", (url.getSubsubmenu() != null) ? url.getSubsubmenu() : "");
				objSubsubmenu.put("subsubmenuicon", (url.getSubsubmenuicon() != null) ? url.getSubsubmenuicon() : "");
				objSubsubmenu.put("pageurl", (url.getSubsubmenu() != null) ? url.getPageurl() : "");
				arrSubSubmenu.add(objSubsubmenu);
				objSubmenu.put("value", arrSubSubmenu);
				//
				arrSubmenu.add(objSubmenu);
				objSubmenu = null;
			} else {
				if (!submenu.contains(url.getSubmenu())) {
					submenu.add(url.getSubmenu());
					if (objSubmenu != null) {
						objSubmenu.put("value", (arrSubSubmenu != null) ? arrSubSubmenu : "");
						arrSubmenu.add(objSubmenu);
					}
					objSubmenu = new JSONObject();
					objSubmenu.put("submenu", url.getSubmenu());
					objSubmenu.put("submenuicon", (url.getSubmenuicon() != null) ? url.getSubmenuicon() : "");
					objSubmenu.put("pageurl", (url.getSubsubmenu() == null) ? url.getPageurl() : "");
					//
					arrSubSubmenu = new JSONArray();
					objSubsubmenu = new JSONObject();
					objSubsubmenu.put("subsubmenu", (url.getSubsubmenu() != null) ? url.getSubsubmenu() : "");
					objSubsubmenu.put("subsubmenuicon",
							(url.getSubsubmenuicon() != null) ? url.getSubsubmenuicon() : "");
					objSubsubmenu.put("pageurl", (url.getSubsubmenu() != null) ? url.getPageurl() : "");
					arrSubSubmenu.add(objSubsubmenu);
					objSubsubmenu = null;
					//
				} else {
					objSubsubmenu = new JSONObject();
					objSubsubmenu.put("subsubmenu", (url.getSubsubmenu() != null) ? url.getSubsubmenu() : "");
					objSubsubmenu.put("subsubmenuicon",
							(url.getSubsubmenuicon() != null) ? url.getSubsubmenuicon() : "");
					objSubsubmenu.put("pageurl", (url.getSubsubmenu() != null) ? url.getPageurl() : "");
					arrSubSubmenu.add(objSubsubmenu);
				}
			}
		}
		if (objSubmenu != null) {
			objSubmenu.put("value", (arrSubSubmenu != null) ? arrSubSubmenu : "");
			arrSubmenu.add(objSubmenu);
		}
		if (objParent != null) {
			objParent.put("value", arrSubmenu);
			arrParent.add(objParent);
		}

		return arrParent;
	}

	public String savePageurl(Pageurls url) {
		if(url.getSubsubmenu()!=null)
			url.setSubsubmenu(url.getSubsubmenu().length() == 0 ? null : url.getSubsubmenu());
		if(url.getSubmenu()!=null)
			url.setSubmenu(url.getSubmenu().length() == 0 ? null : url.getSubmenu());
		return (dao.savePageurlsDao(url)) ? "Saved" : "Failed";
	}

//
	public List<Pageurls> listUrls() {
		return dao.getPageurls();
	}

//
	@Override
	public String getHeaders() {
		JSONArray jarr = new JSONArray();
		JSONObject jo = null;
		for (Map<String, Object> obj : dao.getHeaders()) {
			jo = new JSONObject();
			jo.put("key", obj.get("parent").toString());
			jo.put("icon", (obj.get("parenticon") != null) ? obj.get("parenticon").toString() : "");
			jarr.add(jo);
		}
		return jarr.toJSONString();
	}

	public List<Map<String, Object>> getSubmenu(String parent) {
		return dao.getSubmenu(parent);
	}

	public List<User> listUserAndMappedPages() {

		List<User> list = admindao.listUsers();
		for (User user : list) {
			user.setMappedpages(dao.getMappedPageurls(user.getUsername()));
		}
		return list;
	}

	public String saveUserpages(List<UserPages> upages) {

		return (dao.mapUserpages(upages)) ? "Mapped" : "Failed";
	}
}
