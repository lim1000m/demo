package com.ese.domain; 

public class User {

	public String userId = "";

	public String dvsnCd = "";

	public String regDt = "";

	public String getUserId() {
		return userId;
	}

	public String getDvsnCd() {
		return dvsnCd;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setUserId(String userId) { 
		this.userId = userId;
	}

	public void setDvsnCd(String dvsnCd) { 
		this.dvsnCd = dvsnCd;
	}

	public void setRegDt(String regDt) { 
		this.regDt = regDt;
	}

}