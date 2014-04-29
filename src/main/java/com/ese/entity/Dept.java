package com.ese.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * An item in an order
 */
@Entity
public class Dept {

	@Id
	private int dept_cd;

	@Column(length=100, nullable=false)
	private String dept_nm;

	public int getDept_cd() {
		return dept_cd;
	}

	public void setDept_cd(int dept_cd) {
		this.dept_cd = dept_cd;
	}

	public String getDept_nm() {
		return dept_nm;
	}

	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}


}
