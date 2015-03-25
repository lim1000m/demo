package com.ese.domain; 

public class RefDrwngTbl {

	public int drwngSerno;

	public int refDrwngSerno;

	public int refSerno;

	public String regPsnId = "";

	public String regDt = "";

	public String refDrwngDesc = "";

	public String refFlag = "";

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public int getRefDrwngSerno() {
		return refDrwngSerno;
	}

	public int getRefSerno() {
		return refSerno;
	}

	public String getRegPsnId() {
		return regPsnId;
	}

	public String getRegDt() {
		return regDt;
	}

	public String getRefDrwngDesc() {
		return refDrwngDesc;
	}

	public String getRefFlag() {
		return refFlag;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setRefDrwngSerno(int refDrwngSerno) { 
		this.refDrwngSerno = refDrwngSerno;
	}

	public void setRefSerno(int refSerno) { 
		this.refSerno = refSerno;
	}

	public void setRegPsnId(String regPsnId) { 
		this.regPsnId = regPsnId;
	}

	public void setRegDt(String regDt) { 
		this.regDt = regDt;
	}

	public void setRefDrwngDesc(String refDrwngDesc) { 
		this.refDrwngDesc = refDrwngDesc;
	}

	public void setRefFlag(String refFlag) { 
		this.refFlag = refFlag;
	}

}