package com.ese.entity;


import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * An order.
 */
@Entity
public class ProDept {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int proDeptId;
	
	private String proDeptNm;
	
	private String proLvl;
	
	private String hrnkProId;
	
	private String proOrder;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="proDept")
	private Collection<Item> items = new LinkedHashSet<Item>();

	public int getProDeptId() {
		return proDeptId;
	}

	public void setProDeptId(int proDeptId) {
		this.proDeptId = proDeptId;
	}

	public String getProDeptNm() {
		return proDeptNm;
	}

	public void setProDeptNm(String proDeptNm) {
		this.proDeptNm = proDeptNm;
	}

	public String getProLvl() {
		return proLvl;
	}

	public void setProLvl(String proLvl) {
		this.proLvl = proLvl;
	}

	public String getHrnkProId() {
		return hrnkProId;
	}

	public void setHrnkProId(String hrnkProId) {
		this.hrnkProId = hrnkProId;
	}

	public String getProOrder() {
		return proOrder;
	}

	public void setProOrder(String proOrder) {
		this.proOrder = proOrder;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
}
