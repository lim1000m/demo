package com.ese.domain; 

public class DrwngHstryIndex {

	public int drwngSerno;

	public int drwngHstryNo;

	public String indexFlag = "";

	public String drwngIndex = "";

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public int getDrwngHstryNo() {
		return drwngHstryNo;
	}

	public String getIndexFlag() {
		return indexFlag;
	}

	public String getDrwngIndex() {
		return drwngIndex;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setDrwngHstryNo(int drwngHstryNo) { 
		this.drwngHstryNo = drwngHstryNo;
	}

	public void setIndexFlag(String indexFlag) { 
		this.indexFlag = indexFlag;
	}

	public void setDrwngIndex(String drwngIndex) { 
		this.drwngIndex = drwngIndex;
	}

}