package com.ese.domain; 

public class ApprMngTbl {

	public int apprSerno;

	public String apprStt = "";

	public String apprReqDt = "";

	public String apprReqPsn = "";

	public String ttl = "";

	public String cntnts = "";

	public String apprDvsn = "";

	public int getApprSerno() {
		return apprSerno;
	}

	public String getApprStt() {
		return apprStt;
	}

	public String getApprReqDt() {
		return apprReqDt;
	}

	public String getApprReqPsn() {
		return apprReqPsn;
	}

	public String getTtl() {
		return ttl;
	}

	public String getCntnts() {
		return cntnts;
	}

	public String getApprDvsn() {
		return apprDvsn;
	}

	public void setApprSerno(int apprSerno) { 
		this.apprSerno = apprSerno;
	}

	public void setApprStt(String apprStt) { 
		this.apprStt = apprStt;
	}

	public void setApprReqDt(String apprReqDt) { 
		this.apprReqDt = apprReqDt;
	}

	public void setApprReqPsn(String apprReqPsn) { 
		this.apprReqPsn = apprReqPsn;
	}

	public void setTtl(String ttl) { 
		this.ttl = ttl;
	}

	public void setCntnts(String cntnts) { 
		this.cntnts = cntnts;
	}

	public void setApprDvsn(String apprDvsn) { 
		this.apprDvsn = apprDvsn;
	}

}