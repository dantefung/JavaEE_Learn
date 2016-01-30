package com.dantefung.mybatis_bidirection_association.entity;

import java.io.Serializable;

public class Emp implements Serializable {

	private static final long serialVersionUID = -1263182742758616622L;
	private String empId;//编号
	private String empName;//名称
	private String empSex;//性别
	private Dept dept;//员工所在部门	

	@Override
	public String toString() {
		return "Emp [empId=" + empId + ", empName=" + empName + ", empSex="
				+ empSex + ", dept=" + dept + "]";
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}


}
