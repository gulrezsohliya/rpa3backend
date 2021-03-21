package rpa.Models.Examination;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Advertisement_Categorywise_Age implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer slno;
	private Integer adcode;
	private Integer categorycode;

	public Integer getSlno() {
		return slno;
	}

	public void setSlno(Integer slno) {
		this.slno = slno;
	}

	public Integer getAdcode() {
		return adcode;
	}

	public void setAdcode(Integer adcode) {
		this.adcode = adcode;
	}

	public Integer getCategorycode() {
		return categorycode;
	}

	public void setCategorycode(Integer categorycode) {
		this.categorycode = categorycode;
	}

	@Override
	public String toString() {
		return "Advertisement_Category_Age [slno=" + slno + ", adcode=" + adcode + ", categorycode=" + categorycode
				+ "]";
	}

}
