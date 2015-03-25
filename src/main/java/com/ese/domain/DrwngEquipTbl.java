package com.ese.domain; 

public class DrwngEquipTbl {

	public int drwngSerno;

	public String instanceNumber = "";

	public int maintenanceObjectId;

	public int getDrwngSerno() {
		return drwngSerno;
	}

	public String getInstanceNumber() {
		return instanceNumber;
	}

	public int getMaintenanceObjectId() {
		return maintenanceObjectId;
	}

	public void setDrwngSerno(int drwngSerno) { 
		this.drwngSerno = drwngSerno;
	}

	public void setInstanceNumber(String instanceNumber) { 
		this.instanceNumber = instanceNumber;
	}

	public void setMaintenanceObjectId(int maintenanceObjectId) { 
		this.maintenanceObjectId = maintenanceObjectId;
	}

}