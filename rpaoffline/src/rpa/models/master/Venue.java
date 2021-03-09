package rpa.models.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Venue extends ExamCenter implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer centercode;
	private Integer venuecode;
	private String venuename;

	public Integer getCentercode() {
		return centercode;
	}

	public void setCentercode(Integer centercode) {
		this.centercode = centercode;
	}

	public Integer getVenuecode() {
		return venuecode;
	}

	public void setVenuecode(Integer venuecode) {
		this.venuecode = venuecode;
	}

	public String getVenuename() {
		return venuename;
	}

	public void setVenuename(String venuename) {
		this.venuename = venuename;
	}

	@Override
	public String toString() {
		return "Venue [centercode=" + centercode + ", venuecode=" + venuecode + ", venuename=" + venuename + "]";
	}

}
