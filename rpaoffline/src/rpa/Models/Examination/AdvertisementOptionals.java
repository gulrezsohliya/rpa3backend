package rpa.Models.Examination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdvertisementOptionals implements Serializable {
	private final static long serialVersionUID = 4L;

	private String adcode;
	private Integer optionalcode;
	private Integer optionalsubjectcode;
	private List<HashMap<String, Object>> optionalsubject;

	public AdvertisementOptionals() {
		super();
	}

	public AdvertisementOptionals(Integer optionalcode) {
		super();
		HashMap<String, Object> os = new HashMap<String, Object>();
		os.put("optionalsubjectcode", 0);
		this.optionalsubject = Collections.singletonList(os);
		this.optionalcode = optionalcode;
	}

	public AdvertisementOptionals(Integer optionalcode,String adcode) {
		super();
		this.optionalsubject = new ArrayList<HashMap<String,Object>>();
		this.optionalcode = optionalcode;
		this.adcode = adcode;
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

	public List<HashMap<String, Object>> getOptionalsubject() {
		return optionalsubject;
	}

	public void setOptionalsubject(List<HashMap<String, Object>> optionalsubject) {
		this.optionalsubject = optionalsubject;
	}

	public Integer getOptionalsubjectcode() {
		return optionalsubjectcode;
	}

	public void setOptionalsubjectcode(Integer optionalsubjectcode) {
		this.optionalsubjectcode = optionalsubjectcode;
	}

	@Override
	public String toString() {
		return "AdvertisementOptionals [adcode=" + adcode + ", optionalcode=" + optionalcode + ", optionalsubject="
				+ optionalsubject + "]";
	}

}
