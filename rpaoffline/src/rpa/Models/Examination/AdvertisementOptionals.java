package rpa.Models.Examination;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementOptionals implements Serializable {
	private final static long serialVersionUID = 4L;

	private String adcode;
	private Integer optionalcode;
	private Integer optionalsubjectcode;

	public AdvertisementOptionals() {
		super();
	}

	public AdvertisementOptionals(Integer optionalcode) {
		super();
		this.optionalcode = optionalcode;
		this.optionalsubjectcode=0;
	}

	public String getAdcode() {
		return adcode;
	}

	public void setAdcode(String adcode) {
		this.adcode = adcode;
	}

	public Integer getOptionalcode() {
		return optionalcode;
	}

	public void setOptionalcode(Integer optionalcode) {
		this.optionalcode = optionalcode;
	}

	public Integer getOptionalsubjectcode() {
		return optionalsubjectcode;
	}

	public void setOptionalsubjectcode(Integer optionalsubjectcode) {
		this.optionalsubjectcode = optionalsubjectcode;
	}

	@Override
	public String toString() {
		return "AdvertisementOptionals [adcode=" + adcode + ", optionalcode=" + optionalcode + ", optionalsubjectcode="
				+ optionalsubjectcode + "]";
	}

}
