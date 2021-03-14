package rpa.Models.master;

import java.io.Serializable;

public class Office implements Serializable {
	private final static long serialVersionUID = 1L;

	private Integer officecode;
	private String officename1;
	private String officename2;
	private String officename3;
	private String officeshortname;
	private String signatoryname;
	private String signatorydesignation;
	private String emailid;
	private String emailidpassword;
	private String smsusername;
	private String smspassword;
	private String smssenderid;
	private String enabled;

	public Office() {
		super();
	}

	public void setOffice(Office office) {
		this.officecode = office.getOfficecode();
		this.officename1 = office.getOfficename1();
		this.officename2 = office.getOfficename2();
		this.officename3 = office.getOfficename3();
		this.officeshortname = office.getOfficeshortname();
		this.signatoryname = office.getSignatoryname();
		this.signatorydesignation = office.getSignatorydesignation();
		this.emailid = office.getEmailid();
		this.emailidpassword = office.getEmailidpassword();
		this.smsusername = office.getSmsusername();
		this.smspassword = office.getSmspassword();
		this.smssenderid = office.getSmssenderid();
		this.enabled = office.getEnabled();
	}

	public Integer getOfficecode() {
		return officecode;
	}

	public void setOfficecode(Integer officecode) {
		this.officecode = officecode;
	}

	public String getOfficename1() {
		return officename1;
	}

	public void setOfficename1(String officename1) {
		this.officename1 = officename1;
	}

	public String getOfficename2() {
		return officename2;
	}

	public void setOfficename2(String officename2) {
		this.officename2 = officename2;
	}

	public String getOfficename3() {
		return officename3;
	}

	public void setOfficename3(String officename3) {
		this.officename3 = officename3;
	}

	public String getOfficeshortname() {
		return officeshortname;
	}

	public void setOfficeshortname(String officeshortname) {
		this.officeshortname = officeshortname;
	}

	public String getSignatoryname() {
		return signatoryname;
	}

	public void setSignatoryname(String signatoryname) {
		this.signatoryname = signatoryname;
	}

	public String getSignatorydesignation() {
		return signatorydesignation;
	}

	public void setSignatorydesignation(String signatorydesignation) {
		this.signatorydesignation = signatorydesignation;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getEmailidpassword() {
		return emailidpassword;
	}

	public void setEmailidpassword(String emailidpassword) {
		this.emailidpassword = emailidpassword;
	}

	public String getSmsusername() {
		return smsusername;
	}

	public void setSmsusername(String smsusername) {
		this.smsusername = smsusername;
	}

	public String getSmspassword() {
		return smspassword;
	}

	public void setSmspassword(String smspassword) {
		this.smspassword = smspassword;
	}

	public String getSmssenderid() {
		return smssenderid;
	}

	public void setSmssenderid(String smssenderid) {
		this.smssenderid = smssenderid;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Office [officecode=" + officecode + ", officename1=" + officename1 + ", officename2=" + officename2
				+ ", officename3=" + officename3 + ", officeshortname=" + officeshortname + ", signatoryname="
				+ signatoryname + ", signatorydesignation=" + signatorydesignation + ", emailid=" + emailid
				+ ", emailidpassword=" + emailidpassword + ", smsusername=" + smsusername + ", smspassword="
				+ smspassword + ", smssenderid=" + smssenderid + ", enabled=" + enabled + "]";
	}

}
