package rpa.Models.Examination;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionalSubjects implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer subjectcode;
	private String subjectname;

	public Integer getSubjectcode() {
		return subjectcode;
	}

	public void setSubjectcode(Integer subjectcode) {
		this.subjectcode = subjectcode;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OptionalSubjects [subjectcode=" + subjectcode + ", subjectname=" + subjectname + "]";
	}

}
