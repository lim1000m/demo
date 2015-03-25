package com.ese.domain; 

public class RssiBomRelPart {

	public int bomTreeId;

	public String partCd = "";

	public String bomCd = "";

	public String epln = "";

	public int getBomTreeId() {
		return bomTreeId;
	}

	public String getPartCd() {
		return partCd;
	}

	public String getBomCd() {
		return bomCd;
	}

	public String getEpln() {
		return epln;
	}

	public void setBomTreeId(int bomTreeId) { 
		this.bomTreeId = bomTreeId;
	}

	public void setPartCd(String partCd) { 
		this.partCd = partCd;
	}

	public void setBomCd(String bomCd) { 
		this.bomCd = bomCd;
	}

	public void setEpln(String epln) { 
		this.epln = epln;
	}

}