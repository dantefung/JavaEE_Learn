/**
 * Project Name:DanteFungWeb
 * File Name:Test2One.java
 * Package Name:com.dantefung.mybatis.test
 * Date:2015-11-26下午6:57:39
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
 */

package com.dantefung.mybatis.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dantefung.mybatis_2one.dao.impl.DeptDaoImpl;
import com.dantefung.mybatis_2one.entity.Emp;

/**
 * ClassName:Test2One <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015-11-26 下午6:57:39 <br/>
 * 
 * @author Dante Fung
 * @version
 * @since JDK 1.6
 * @see
 */
public class Test2One
{
	private static DeptDaoImpl deptDaoImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		deptDaoImpl = new DeptDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		deptDaoImpl = null;
	}

	/**
	 * 
	 * testSelectEmpDeptList:(这里用一句话描述这个方法的作用). <br/>
	 * TODO查詢員工關聯查詢出部門.<br/>
	 * 
	 * @author Dante Fung
	 * @since JDK 1.6
	 */
	@Test
	public void testSelectEmpDeptList()
	{
		List<Emp> emps = deptDaoImpl.selectEmpDeptList("%张%");
		for (Emp emp : emps)
		{
			System.out.println("员工信息：" + emp);
		}
	}
}
