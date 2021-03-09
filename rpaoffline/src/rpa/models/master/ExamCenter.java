package rpa.models.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamCenter implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer centercode;
	private String centername;

	public ExamCenter() {
		super();
	}

	public void setExamCenter(ExamCenter center) {
		this.centercode = center.getCentercode();
		this.centername = center.getCentername();
	}

	public Integer getCentercode() {
		return centercode;
	}

	public void setCentercode(Integer centercode) {
		this.centercode = centercode;
	}

	public String getCentername() {
		return centername;
	}

	public void setCentername(String centername) {
		this.centername = centername;
	}

	@Override
	public String toString() {
		return "ExamCenter [centercode=" + centercode + ", centername=" + centername + "]";
	}

}
