package com.ese.domain; 

public class UserInfoTbl {

	public String userName = "";

	public String personName = "";

	public String organizationId = "";

	public String organizationName = "";

	public String positionName = "";

	public String passwd = "";

	public String getUserName() {
		return userName;
	}

	public String getPersonName() {
		return personName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public String getPositionName() {
		return positionName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setUserName(String userName) { 
		this.userName = userName;
	}

	public void setPersonName(String personName) { 
		this.personName = personName;
	}

	public void setOrganizationId(String organizationId) { 
		this.organizationId = organizationId;
	}

	public void setOrganizationName(String organizationName) { 
		this.organizationName = organizationName;
	}

	public void setPositionName(String positionName) { 
		this.positionName = positionName;
	}

	public void setPasswd(String passwd) { 
		this.passwd = passwd;
	}

}