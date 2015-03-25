package com.ese.domain; 

public class DrwngHstryLayer {

	public int drwngSerno;

	public int drwngHstryNo;

	public String drwngLayer = "";

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public int getDrwngHstryNo() {
		return drwngHstryNo;
	}

	public String getDrwngLayer() {
		return drwngLayer;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setDrwngHstryNo(int drwngHstryNo) { 
		this.drwngHstryNo = drwngHstryNo;
	}

	public void setDrwngLayer(String drwngLayer) { 
		this.drwngLayer = drwngLayer;
	}

}