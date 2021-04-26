package rpa.Models.Examination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementFeesRelax implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer slno;
	private String adcode;
	private Integer pwdfees;
	private Integer womanfees;
	private Integer exservicemenfees;
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

	public Integer getPwdfees() {
		return pwdfees;
	}

	public void setPwdfees(Integer pwdfees) {
		this.pwdfees = pwdfees;
	}

	public Integer getWomanfees() {
		return womanfees;
	}

	public void setWomanfees(Integer womanfees) {
		this.womanfees = womanfees;
	}

	public Integer getExservicemenfees() {
		return exservicemenfees;
	}

	public void setExservicemenfees(Integer exservicemenfees) {
		this.exservicemenfees = exservicemenfees;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	@Override
	public String toString() {
		return "AdvertisementFeesRelax [slno=" + slno + ", adcode=" + adcode + ", pwdfees=" + pwdfees + ", womanfees="
				+ womanfees + ", exservicemenfees=" + exservicemenfees + ", entrydate=" + entrydate + "]";
	}

}
