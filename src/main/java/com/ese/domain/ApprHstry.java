package com.ese.domain; 

public class ApprHstry {

	public int apprSerno;

	public String procDt = "";

	public String k = "";

	public int hstrySerno;

	public String apprStt = "";

	public int getApprSerno() {
		return apprSerno;
	}

	public String getProcDt() {
		return procDt;
	}

	public String getK() {
		return k;
	}

	public int getHstrySerno() {
		return hstrySerno;
	}

	public String getApprStt() {
		return apprStt;
	}

	public void setApprSerno(int apprSerno) { 
		this.apprSerno = apprSerno;
	}

	public void setProcDt(String procDt) { 
		this.procDt = procDt;
	}

	public void setK(String k) { 
		this.k = k;
	}

	public void setHstrySerno(int hstrySerno) { 
		this.hstrySerno = hstrySerno;
	}

	public void setApprStt(String apprStt) { 
		this.apprStt = apprStt;
	}

}