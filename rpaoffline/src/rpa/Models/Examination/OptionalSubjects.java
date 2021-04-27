package rpa.Models.Examination;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionalSubjects implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer optionalsubjectcode;
	private String optionalsubjectname;

	public Integer getOptionalsubjectcode() {
		return optionalsubjectcode;
	}

	public void setOptionalsubjectcode(Integer optionalsubjectcode) {
		this.optionalsubjectcode = optionalsubjectcode;
	}

	public String getOptionalsubjectname() {
		return optionalsubjectname;
	}

	public void setOptionalsubjectname(String optionalsubjectname) {
		this.optionalsubjectname = optionalsubjectname;
	}

	@Override
	public String toString() {
		return "OptionalSubjects [optionalsubjectcode=" + optionalsubjectcode + ", optionalsubjectname="
				+ optionalsubjectname + "]";
	}

}
