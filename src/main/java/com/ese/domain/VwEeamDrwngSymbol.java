package com.ese.domain; 

public class VwEeamDrwngSymbol {

	public int drwngSerno;

	public String drwngLayer = "";

	public int symbolCnt;

	public String drwngSymbol = "";

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public String getDrwngLayer() {
		return drwngLayer;
	}

	public int getSymbolCnt() {
		return symbolCnt;
	}

	public String getDrwngSymbol() {
		return drwngSymbol;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setDrwngLayer(String drwngLayer) { 
		this.drwngLayer = drwngLayer;
	}

	public void setSymbolCnt(int symbolCnt) { 
		this.symbolCnt = symbolCnt;
	}

	public void setDrwngSymbol(String drwngSymbol) { 
		this.drwngSymbol = drwngSymbol;
	}

}