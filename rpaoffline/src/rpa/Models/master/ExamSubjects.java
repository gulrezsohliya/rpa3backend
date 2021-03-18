package rpa.Models.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExamSubjects implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer examinationsubjectcode;
	private String examinationsubjectname;
	private String description;

	public Integer getExaminationsubjectcode() {
		return examinationsubjectcode;
	}

	public void setExaminationsubjectcode(Integer examinationsubjectcode) {
		this.examinationsubjectcode = examinationsubjectcode;
	}

	public String getExaminationsubjectname() {
		return examinationsubjectname;
	}

	public void setExaminationsubjectname(String examinationsubjectname) {
		this.examinationsubjectname = examinationsubjectname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ExamSubjects [examinationsubjectcode=" + examinationsubjectcode + ", examinationsubjectname="
				+ examinationsubjectname + ", description=" + description + "]";
	}

}
