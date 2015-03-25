package com.ese.domain; 

public class VwEeamGroup {

	public String groupCd = "";

	public String groupNm = "";

	public String groupKind = "";

	public int groupOrd;

	public int groupSerno;

	public int groupLvl;

	public String dvsnCd = "";

	public int prntGroupSerno;

	public int groupCnt;

	public String getGroupCd() {
		return groupCd;
	}

	public String getGroupNm() {
		return groupNm;
	}

	public String getGroupKind() {
		return groupKind;
	}

	public int getGroupOrd() {
		return groupOrd;
	}

	public int getGroupSerno() {
		return groupSerno;
	}

	public int getGroupLvl() {
		return groupLvl;
	}

	public String getDvsnCd() {
		return dvsnCd;
	}

	public int getPrntGroupSerno() {
		return prntGroupSerno;
	}

	public int getGroupCnt() {
		return groupCnt;
	}

	public void setGroupCd(String groupCd) { 
		this.groupCd = groupCd;
	}

	public void setGroupNm(String groupNm) { 
		this.groupNm = groupNm;
	}

	public void setGroupKind(String groupKind) { 
		this.groupKind = groupKind;
	}

	public void setGroupOrd(int groupOrd) { 
		this.groupOrd = groupOrd;
	}

	public void setGroupSerno(int groupSerno) { 
		this.groupSerno = groupSerno;
	}

	public void setGroupLvl(int groupLvl) { 
		this.groupLvl = groupLvl;
	}

	public void setDvsnCd(String dvsnCd) { 
		this.dvsnCd = dvsnCd;
	}

	public void setPrntGroupSerno(int prntGroupSerno) { 
		this.prntGroupSerno = prntGroupSerno;
	}

	public void setGroupCnt(int groupCnt) { 
		this.groupCnt = groupCnt;
	}

}