/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpa.Services.Admin;

import java.util.ArrayList;
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
import rpa.Models.master.Urls;
import rpa.Models.master.User;
import rpa.Models.master.UserPages;

@Service("PageurlsService")
public class PageurlsService implements PageurlsServiceInterface {

	@Autowired
	PageurlsDaoInterface dao;
	@Autowired
	InitializationDaoInterface admindao;

	@Override
	public List<Urls> getPageurls(Integer usercode) {

		List<Pageurls> urls = dao.getMappedPageurls(usercode);
		List<Urls> arrParent = new ArrayList<Urls>();
		Urls objParent = null;
		Urls objSubmenu = null;
		Urls objSubsubmenu = null;
		List<Urls> arrSubmenu = null;
		List<Urls> arrSubSubmenu = null;
		List<String> parent = new LinkedList<String>();
		List<String> submenu = new LinkedList<String>();
		for (Pageurls url : urls) {

			if (!parent.contains(url.getParent())) {
				parent.add(url.getParent());
				submenu = new LinkedList<String>();
				if (objSubmenu != null) {
					objSubmenu.setSuburls(((arrSubSubmenu != null) ? arrSubSubmenu : null));
					arrSubmenu.add(objSubmenu);
				}
				if (objParent != null) {
					objParent.setSuburls(arrSubmenu);
					arrParent.add(objParent);
				}
				objParent = new Urls();
				objParent.setName(url.getParent());
				objParent.setIcon((url.getParenticon() != null) ? url.getParenticon() : "");
				objParent.setPageurl((url.getSubmenu() == null) ? url.getPageurl() : "");

				arrSubmenu = new ArrayList<Urls>();
				objSubmenu = new Urls();
				submenu.add(url.getSubmenu());
				objSubmenu.setName(url.getSubmenu());
				objSubmenu.setIcon((url.getSubmenuicon() != null) ? url.getSubmenuicon() : "");
				objSubmenu.setPageurl((url.getSubsubmenu() == null) ? url.getPageurl() : "");
				//
				arrSubSubmenu = new ArrayList<Urls>();
				objSubsubmenu = new Urls();
				objSubsubmenu.setName((url.getSubsubmenu() != null) ? url.getSubsubmenu() : "");
				objSubsubmenu.setIcon((url.getSubsubmenuicon() != null) ? url.getSubsubmenuicon() : "");
				objSubsubmenu.setPageurl((url.getSubsubmenu() != null) ? url.getPageurl() : "");
				arrSubSubmenu.add(objSubsubmenu);
				objSubmenu.setSuburls(arrSubSubmenu);
				//
				arrSubmenu.add(objSubmenu);
				objSubmenu = null;
			} else {
				if (!submenu.contains(url.getSubmenu())) {
					submenu.add(url.getSubmenu());
					if (objSubmenu != null) {
						objSubmenu.setSuburls((arrSubSubmenu != null) ? arrSubSubmenu : null);
						arrSubmenu.add(objSubmenu);
					}
					objSubmenu = new Urls();
					objSubmenu.setName(url.getSubmenu());
					objSubmenu.setIcon((url.getSubmenuicon() != null) ? url.getSubmenuicon() : "");
					objSubmenu.setPageurl((url.getSubsubmenu() == null) ? url.getPageurl() : "");
					//
					arrSubSubmenu = new ArrayList<Urls>();
					objSubsubmenu = new Urls();
					objSubsubmenu.setName((url.getSubsubmenu() != null) ? url.getSubsubmenu() : "");
					objSubsubmenu.setIcon((url.getSubsubmenuicon() != null) ? url.getSubsubmenuicon() : "");
					objSubsubmenu.setPageurl((url.getSubsubmenu() != null) ? url.getPageurl() : "");
					arrSubSubmenu.add(objSubsubmenu);
					objSubsubmenu = null;
					//
				} else {
					objSubsubmenu = new Urls();
					objSubsubmenu.setName((url.getSubsubmenu() != null) ? url.getSubsubmenu() : "");
					objSubsubmenu.setIcon((url.getSubsubmenuicon() != null) ? url.getSubsubmenuicon() : "");
					objSubsubmenu.setPageurl((url.getSubsubmenu() != null) ? url.getPageurl() : "");
					arrSubSubmenu.add(objSubsubmenu);
				}
			}
		}
		if (objSubmenu != null) {
			objSubmenu.setSuburls((arrSubSubmenu != null) ? arrSubSubmenu : null);
			arrSubmenu.add(objSubmenu);
		}
		if (objParent != null) {
			objParent.setSuburls(arrSubmenu);
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
			user.setMappedpages(dao.getMappedPageurls(user.getUsercode()));
		}
		return list;
	}

	public String saveUserpages(List<UserPages> upages) {

		return (dao.mapUserpages(upages)) ? "Mapped" : "Failed";
	}
}
