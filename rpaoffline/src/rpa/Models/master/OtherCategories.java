package rpa.Models.master;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherCategories implements Serializable {
	private final static long serialVersionUID = 4L;

	private Integer othercategorycode;
	private String othercategorydescription;

	public Integer getOthercategorycode() {
		return othercategorycode;
	}

	public void setOthercategorycode(Integer othercategorycode) {
		this.othercategorycode = othercategorycode;
	}

	public String getOthercategorydescription() {
		return othercategorydescription;
	}

	public void setOthercategorydescription(String othercategorydescription) {
		this.othercategorydescription = othercategorydescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "OtherCategories [othercategorycode=" + othercategorycode + ", othercategorydescription="
				+ othercategorydescription + "]";
	}

}
