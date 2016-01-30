package com.dantefung.mybatis_2many.entity;

import java.io.Serializable;

public class Emp implements Serializable
{

	private static final long serialVersionUID = -1263182742758616622L;
	private String empId;// 编号
	private String empName;// 名称
	private String empSex;// 性别
	public String getEmpId()
	{
		return empId;
	}

	@Override
	public String toString()
	{
		return "Emp [empId=" + empId + ", empName=" + empName + ", empSex="
				+ empSex + "]";
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	public String getEmpSex()
	{
		return empSex;
	}

	public void setEmpSex(String empSex)
	{
		this.empSex = empSex;
	}


}
