/**
 * Project Name:DanteFungWeb
 * File Name:Student.java
 * Package Name:com.dantefung.springmvc.AbstractCommandController
 * Date:2015-11-28下午7:55:50
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.springmvc.multiactioncontroller;

import java.io.Serializable;

/**
 * ClassName:Student <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-11-28 下午7:55:50 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Student implements Serializable
{

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 7035847399257582306L;

	private String stuId;
	private String stuName;
	private String stuPwd;
	public String getStuId()
	{
		return stuId;
	}
	public void setStuId(String stuId)
	{
		this.stuId = stuId;
	}
	public String getStuName()
	{
		return stuName;
	}
	public void setStuName(String stuName)
	{
		this.stuName = stuName;
	}
	public String getStuPwd()
	{
		return stuPwd;
	}
	public void setStuPwd(String stuPwd)
	{
		this.stuPwd = stuPwd;
	}
	@Override
	public String toString()
	{
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", stuPwd="
				+ stuPwd + "]";
	}
	
	
}

