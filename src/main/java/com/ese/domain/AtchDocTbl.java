package com.ese.domain; 

public class AtchDocTbl {

	public int drwngSerno;

	public int atchDocSerno;

	public String atchDocNm = "";

	public String atchDocDesc = "";

	public String regDt = "";

	public String regPsnId = "";

	public String modPsnId = "";

	public String modDt = "";

	public String atchDocFileNm = "";

	public String atchDocSavePath = "";

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public int getAtchDocSerno() {
		return atchDocSerno;
	}

	public String getAtchDocNm() {
		return atchDocNm;
	}

	public String getAtchDocDesc() {
		return atchDocDesc;
	}

	public String getRegDt() {
		return regDt;
	}

	public String getRegPsnId() {
		return regPsnId;
	}

	public String getModPsnId() {
		return modPsnId;
	}

	public String getModDt() {
		return modDt;
	}

	public String getAtchDocFileNm() {
		return atchDocFileNm;
	}

	public String getAtchDocSavePath() {
		return atchDocSavePath;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setAtchDocSerno(int atchDocSerno) { 
		this.atchDocSerno = atchDocSerno;
	}

	public void setAtchDocNm(String atchDocNm) { 
		this.atchDocNm = atchDocNm;
	}

	public void setAtchDocDesc(String atchDocDesc) { 
		this.atchDocDesc = atchDocDesc;
	}

	public void setRegDt(String regDt) { 
		this.regDt = regDt;
	}

	public void setRegPsnId(String regPsnId) { 
		this.regPsnId = regPsnId;
	}

	public void setModPsnId(String modPsnId) { 
		this.modPsnId = modPsnId;
	}

	public void setModDt(String modDt) { 
		this.modDt = modDt;
	}

	public void setAtchDocFileNm(String atchDocFileNm) { 
		this.atchDocFileNm = atchDocFileNm;
	}

	public void setAtchDocSavePath(String atchDocSavePath) { 
		this.atchDocSavePath = atchDocSavePath;
	}

}