package rpa.Models.Examination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementFees implements Serializable {
	@Override
	public String toString() {
		return "AdvertisementFees [slno=" + slno + ", adcode=" + adcode + ", categorycode=" + categorycode
				+ ", feeamount=" + feeamount + ", entrydate=" + entrydate + "]";
	}

	private final static long serialVersionUID = 4L;

	private Integer slno;
	private String adcode;
	private String categorycode;
	private Integer feeamount;
	private Date entrydate;

	public AdvertisementFees() {
		super();
		this.categorycode = "GENERAL";
		this.feeamount = 0;
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

	public Integer getFeeamount() {
		return feeamount;
	}

	public void setFeeamount(Integer feeamount) {
		this.feeamount = feeamount;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

}
