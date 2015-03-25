package com.ese.domain; 

public class DeptTbl {

	public String deptCd = "";

	public String dvsnCd = "";

	public String deptNm = "";

	public String description = "";

	public String getDeptCd() {
		return deptCd;
	}

	public String getDvsnCd() {
		return dvsnCd;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public String getDescription() {
		return description;
	}

	public void setDeptCd(String deptCd) { 
		this.deptCd = deptCd;
	}

	public void setDvsnCd(String dvsnCd) { 
		this.dvsnCd = dvsnCd;
	}

	public void setDeptNm(String deptNm) { 
		this.deptNm = deptNm;
	}

	public void setDescription(String description) { 
		this.description = description;
	}

}