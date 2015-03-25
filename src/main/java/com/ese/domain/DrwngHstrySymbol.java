package com.ese.domain; 

public class DrwngHstrySymbol {

	public int drwngSerno;

	public int drwngHstryNo;

	public String drwngSymbol = "";

	public String drwngLayer = "";

	public int symbolCnt;

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public int getDrwngHstryNo() {
		return drwngHstryNo;
	}

	public String getDrwngSymbol() {
		return drwngSymbol;
	}

	public String getDrwngLayer() {
		return drwngLayer;
	}

	public int getSymbolCnt() {
		return symbolCnt;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setDrwngHstryNo(int drwngHstryNo) { 
		this.drwngHstryNo = drwngHstryNo;
	}

	public void setDrwngSymbol(String drwngSymbol) { 
		this.drwngSymbol = drwngSymbol;
	}

	public void setDrwngLayer(String drwngLayer) { 
		this.drwngLayer = drwngLayer;
	}

	public void setSymbolCnt(int symbolCnt) { 
		this.symbolCnt = symbolCnt;
	}

}