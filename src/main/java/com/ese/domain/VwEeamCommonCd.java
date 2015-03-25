package com.ese.domain; 

public class VwEeamCommonCd {

	public String typeCd = "";

	public String typeNm = "";

	public String cmCd = "";

	public String cmNm = "";

	public int cmOrder;

	public String cdGb = "";

	public String getTypeCd() {
		return typeCd;
	}

	public String getTypeNm() {
		return typeNm;
	}

	public String getCmCd() {
		return cmCd;
	}

	public String getCmNm() {
		return cmNm;
	}

	public int getCmOrder() {
		return cmOrder;
	}

	public String getCdGb() {
		return cdGb;
	}

	public void setTypeCd(String typeCd) { 
		this.typeCd = typeCd;
	}

	public void setTypeNm(String typeNm) { 
		this.typeNm = typeNm;
	}

	public void setCmCd(String cmCd) { 
		this.cmCd = cmCd;
	}

	public void setCmNm(String cmNm) { 
		this.cmNm = cmNm;
	}

	public void setCmOrder(int cmOrder) { 
		this.cmOrder = cmOrder;
	}

	public void setCdGb(String cdGb) { 
		this.cdGb = cdGb;
	}

}