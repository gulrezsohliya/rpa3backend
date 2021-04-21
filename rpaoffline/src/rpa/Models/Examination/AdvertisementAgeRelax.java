package rpa.Models.Examination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementAgeRelax implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer slno;
	private String adcode;
	private Integer pwdadditionalage;
	private Integer womanadditionalage;
	private Integer exservicemenadditionalage;
	private Date entrydate;

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

	public Integer getPwdadditionalage() {
		return pwdadditionalage;
	}

	public void setPwdadditionalage(Integer pwdadditionalage) {
		this.pwdadditionalage = pwdadditionalage;
	}

	public Integer getWomanadditionalage() {
		return womanadditionalage;
	}

	public void setWomanadditionalage(Integer womanadditionalage) {
		this.womanadditionalage = womanadditionalage;
	}

	public Integer getExservicemenadditionalage() {
		return exservicemenadditionalage;
	}

	public void setExservicemenadditionalage(Integer exservicemenadditionalage) {
		this.exservicemenadditionalage = exservicemenadditionalage;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

}
