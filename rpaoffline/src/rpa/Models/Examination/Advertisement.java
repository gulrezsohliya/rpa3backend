package rpa.Models.Examination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Advertisement implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer officecode;
	private Integer slno;
	private String adcode;
	private String nameofpost;
	private String postshortname;
	private Date issuedate;
	private Date lastdate;
	private Date agedate;
	private String description;
	private String counterentry;
	private String open;
	private Integer noofoptionals;
	private Integer usercode;
	private Date entrydate;
	private String finalized;
	private String feetype;
	private String advertisementno;
	private Integer examinationmodecode;
//	private List<Advertisement_Categorywise_Age> categorywise_Age;

	public Integer getOfficecode() {
		return officecode;
	}

	public void setOfficecode(Integer officecode) {
		this.officecode = officecode;
	}

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public String getNameofpost() {
		return nameofpost;
	}

	public void setNameofpost(String nameofpost) {
		this.nameofpost = nameofpost;
	}

	public String getPostshortname() {
		return postshortname;
	}

	public void setPostshortname(String postshortname) {
		this.postshortname = postshortname;
	}

	public Date getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}

	public Date getLastdate() {
		return lastdate;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	public Date getAgedate() {
		return agedate;
	}

	public void setAgedate(Date agedate) {
		this.agedate = agedate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCounterentry() {
		return counterentry;
	}

	public void setCounterentry(String counterentry) {
		this.counterentry = counterentry;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public Integer getNoofoptionals() {
		return noofoptionals;
	}

	public void setNoofoptionals(Integer noofoptionals) {
		this.noofoptionals = noofoptionals;
	}

	public Integer getUsercode() {
		return usercode;
	}

	public void setUsercode(Integer usercode) {
		this.usercode = usercode;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getFinalized() {
		return finalized;
	}

	public void setFinalized(String finalized) {
		this.finalized = finalized;
	}

	public String getFeetype() {
		return feetype;
	}

	public void setFeetype(String feetype) {
		this.feetype = feetype;
	}

	public String getAdvertisementno() {
		return advertisementno;
	}

	public void setAdvertisementno(String advertisementno) {
		this.advertisementno = advertisementno;
	}

	public Integer getExaminationmodecode() {
		return examinationmodecode;
	}

	public void setExaminationmodecode(Integer examinationmodecode) {
		this.examinationmodecode = examinationmodecode;
	}

	@Override
	public String toString() {
		return "Advertisement [officecode=" + officecode + ", slno=" + slno + ", adcode=" + adcode + ", nameofpost="
				+ nameofpost + ", postshortname=" + postshortname + ", issuedate=" + issuedate + ", lastdate="
				+ lastdate + ", agedate=" + agedate + ", description=" + description + ", counterentry=" + counterentry
				+ ", open=" + open +  ", noofoptionals=" + noofoptionals + ", usercode=" + usercode
				+ ", entrydate=" + entrydate + ", finalized=" + finalized + ", feetype=" + feetype
				+ ", advertisementno=" + advertisementno + ", examinationmodecode=" + examinationmodecode + "]";
	}

}
