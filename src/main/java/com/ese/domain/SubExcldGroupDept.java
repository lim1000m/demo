package com.ese.domain; 

public class SubExcldGroupDept {

	public String dvsnCd = "";

	public int groupSerno;

	public String deptCd = "";

	public String getDvsnCd() {
		return dvsnCd;
	}

	public int getGroupSerno() {
		return groupSerno;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDvsnCd(String dvsnCd) { 
		this.dvsnCd = dvsnCd;
	}

	public void setGroupSerno(int groupSerno) { 
		this.groupSerno = groupSerno;
	}

	public void setDeptCd(String deptCd) { 
		this.deptCd = deptCd;
	}

}