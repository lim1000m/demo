package com.ese.domain; 

public class LogTbl {

	public int logSerno;

	public String regDt = "";

	public String userId = "";

	public String drwngNm = "";

	public String logFlag = "";

	public int drwngSerno;

	public String remk = "";

	public int getLogSerno() {
		return logSerno;
	}

	public String getRegDt() {
		return regDt;
	}

	public String getUserId() {
		return userId;
	}

	public String getDrwngNm() {
		return drwngNm;
	}

	public String getLogFlag() {
		return logFlag;
	}

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public String getRemk() {
		return remk;
	}

	public void setLogSerno(int logSerno) { 
		this.logSerno = logSerno;
	}

	public void setRegDt(String regDt) { 
		this.regDt = regDt;
	}

	public void setUserId(String userId) { 
		this.userId = userId;
	}

	public void setDrwngNm(String drwngNm) { 
		this.drwngNm = drwngNm;
	}

	public void setLogFlag(String logFlag) { 
		this.logFlag = logFlag;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setRemk(String remk) { 
		this.remk = remk;
	}

}