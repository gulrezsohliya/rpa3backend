package rpa.Models.Examination;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficeCenter implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer slno;
	private Integer officecode;
	private Integer centercode;

	public OfficeCenter() {
		super();
	}

	public OfficeCenter(Integer officecode, Integer centercode) {
		super();
		this.officecode = officecode;
		this.centercode = centercode;
	}

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public Integer getOfficecode() {
		return officecode;
	}

	public void setOfficecode(Integer officecode) {
		this.officecode = officecode;
	}

	public Integer getCentercode() {
		return centercode;
	}

	public void setCentercode(Integer centercode) {
		this.centercode = centercode;
	}

	@Override
	public String toString() {
		return "OfficeCenter [slno=" + slno + ", officecode=" + officecode + ", centercode=" + centercode + "]";
	}

}
