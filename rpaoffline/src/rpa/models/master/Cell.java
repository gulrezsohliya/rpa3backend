package rpa.models.master;

import java.io.Serializable;

public class Cell extends Office implements Serializable{
	private final static long serialVersionUID = 1L;
	
	private String cellcode;
	private String celldescription;
	private String enabled;

	public String getCellcode() {
		return cellcode;
	}

	public void setCellcode(String cellcode) {
		this.cellcode = cellcode;
	}

	public String getCelldescription() {
		return celldescription;
	}

	public void setCelldescription(String celldescription) {
		this.celldescription = celldescription;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Cell [cellcode=" + cellcode + ", celldescription=" + celldescription + ", enabled=" + enabled
				+ ", getOfficecode()=" + getOfficecode() + "]";
	}

}
