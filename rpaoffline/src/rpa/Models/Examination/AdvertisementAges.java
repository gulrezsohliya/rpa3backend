package rpa.Models.Examination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementAges implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer slno;
	private String adcode;
	private String categorycode;
	private Integer minage;
	private Integer maxage;
	private Date entrydate;

	public AdvertisementAges() {
		super();
		this.categorycode = "GENERAL";
		this.minage = 0;
		this.maxage = 0;
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

	public String getCategorycode() {
		return categorycode;
	}

	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}

	public Integer getMinage() {
		return minage;
	}

	public void setMinage(Integer minage) {
		this.minage = minage;
	}

	public Integer getMaxage() {
		return maxage;
	}

	public void setMaxage(Integer maxage) {
		this.maxage = maxage;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	@Override
	public String toString() {
		return "AdvertisementAges [slno=" + slno + ", adcode=" + adcode + ", categorycode=" + categorycode + ", minage="
				+ minage + ", maxage=" + maxage + ", entrydate=" + entrydate + "]";
	}

}
