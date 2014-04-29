package com.ese.entity;


import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * An item in an order
 */
@Entity //테이블임을 표시
@SequenceGenerator(sequenceName ="EMPLOYEE.ID_SEQ", name="employeeSeq")
public class employee {
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "employeeSeq")
	@Id 
	private int id;

	@Column(name="first_name")
	private String first_name;
	
	@Column(name="last_name")
	private String last_name;
	
	@Column(name="emp_no", length=100, nullable=false, unique=true) 
	private String emp_no;
	
	@Column(name="dept_no", length=100, nullable=true, unique=true)
	private String dept_no;
	
	@Column(name="position", length=100, nullable=true, unique=true)
	private String position;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	private Collection<Dept> dept = new LinkedHashSet<Dept>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getDept_no() {
		return dept_no;
	}

	public void setDept_no(String dept_no) {
		this.dept_no = dept_no;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
