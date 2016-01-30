package com.dantefung.mybatis_2many.entity;

import java.io.Serializable;
import java.util.List;

public class Dept implements Serializable
{

	private static final long serialVersionUID = -2525513725816258556L;

	private Integer deptId;// 部门编号
	private String deptName;// 部门名称
	private String deptAddress;// 部门地址
	private List<Emp> emps;
	@Override
	public String toString()
	{
		return "Dept [deptId=" + deptId + ", deptName=" + deptName
				+ ", deptAddress=" + deptAddress +"]";
	}


	public Integer getDeptId()
	{
		return deptId;
	}

	public void setDeptId(Integer deptId)
	{
		this.deptId = deptId;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getDeptAddress()
	{
		return deptAddress;
	}

	public void setDeptAddress(String deptAddress)
	{
		this.deptAddress = deptAddress;
	}


	public List<Emp> getEmps()
	{
		return emps;
	}


	public void setEmps(List<Emp> emps)
	{
		this.emps = emps;
	}

}
