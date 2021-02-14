package rpa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPages {

	private int userpagecode;
	private int usercode;
	private Pageurls url;

	public UserPages() {
	}

//    public UserPages(model.persitent.UserPages u) {
//        this.userpagecode = u.getUserpagecode();
//        this.usercode = u.getUser().getUsercode();
//        this.url = new Pageurls(u.getUrl());
//    }

	public int getUserpagecode() {
		return userpagecode;
	}

	public void setUserpagecode(int userpagecode) {
		this.userpagecode = userpagecode;
	}

	public int getUsercode() {
		return usercode;
	}

	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}

	public Pageurls getUrl() {
		return url;
	}

	public void setUrl(Pageurls url) {
		this.url = url;
	}

}
