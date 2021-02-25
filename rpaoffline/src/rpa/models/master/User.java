package rpa.models.master;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class User {

	private Integer cellcode;
	private Integer usercode;
	private String username;
	private String passwords;
	private String fullname;
	private String mobileno;
	private String designation;
	private String enabled;
	@JsonSerialize(using = DateSerializer.class)
	private Date entrydate;
	private List<Pageurls> mappedpages;

	public Integer getCellcode() {
		return cellcode;
	}

	public void setCellcode(Integer cellcode) {
		this.cellcode = cellcode;
	}

	public Integer getUsercode() {
		return usercode;
	}

	public void setUsercode(Integer usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswords() {
		return passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public List<Pageurls> getMappedpages() {
		return mappedpages;
	}

	public void setMappedpages(List<Pageurls> mappedpages) {
		this.mappedpages = mappedpages;
	}
	
	@Override
	public String toString() {
		return "User [cellcode=" + cellcode + ", usercode=" + usercode + ", username=" + username + ", passwords="
				+ passwords + ", fullname=" + fullname + ", mobileno=" + mobileno + ", designation=" + designation
				+ ", enabled=" + enabled + ", entrydate=" + entrydate + ", mappedpages=" + mappedpages + "]";
	}
}
