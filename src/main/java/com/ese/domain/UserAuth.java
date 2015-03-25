package com.ese.domain; 

public class UserAuth {

	public String userId = "";

	public String dvsnCd = "";

	public String readAuth = "";

	public String viewAuth = "";

	public String writeAuth = "";

	public String modifyAuth = "";

	public String revisionAuth = "";

	public String deleteAuth = "";

	public String downloadAuth = "";

	public String printAuth = "";

	public String getUserId() {
		return userId;
	}

	public String getDvsnCd() {
		return dvsnCd;
	}

	public String getReadAuth() {
		return readAuth;
	}

	public String getViewAuth() {
		return viewAuth;
	}

	public String getWriteAuth() {
		return writeAuth;
	}

	public String getModifyAuth() {
		return modifyAuth;
	}

	public String getRevisionAuth() {
		return revisionAuth;
	}

	public String getDeleteAuth() {
		return deleteAuth;
	}

	public String getDownloadAuth() {
		return downloadAuth;
	}

	public String getPrintAuth() {
		return printAuth;
	}

	public void setUserId(String userId) { 
		this.userId = userId;
	}

	public void setDvsnCd(String dvsnCd) { 
		this.dvsnCd = dvsnCd;
	}

	public void setReadAuth(String readAuth) { 
		this.readAuth = readAuth;
	}

	public void setViewAuth(String viewAuth) { 
		this.viewAuth = viewAuth;
	}

	public void setWriteAuth(String writeAuth) { 
		this.writeAuth = writeAuth;
	}

	public void setModifyAuth(String modifyAuth) { 
		this.modifyAuth = modifyAuth;
	}

	public void setRevisionAuth(String revisionAuth) { 
		this.revisionAuth = revisionAuth;
	}

	public void setDeleteAuth(String deleteAuth) { 
		this.deleteAuth = deleteAuth;
	}

	public void setDownloadAuth(String downloadAuth) { 
		this.downloadAuth = downloadAuth;
	}

	public void setPrintAuth(String printAuth) { 
		this.printAuth = printAuth;
	}

}