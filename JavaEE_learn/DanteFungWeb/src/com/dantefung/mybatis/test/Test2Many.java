/**
 * Project Name:DanteFungWeb
 * File Name:Test2Many.java
 * Package Name:com.dantefung.mybatis.test
 * Date:2015-11-26下午7:37:45
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.mybatis.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dantefung.mybatis_2many.entity.Dept;
import com.dantefung.mybatis_2many.dao.impl.DeptDaoImpl;

/**
 * ClassName:Test2Many <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-11-26 下午7:37:45 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Test2Many
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
	 * TODO查询部门关联查询出该部门下的所有员工<br/>
	 * 
	 * @author Dante Fung
	 * @since JDK 1.6
	 */
	@Test
	public void testSelectDepEmptList()
	{
		List<Dept> depts = deptDaoImpl.selectDeptEmpList("%研%");
		for (Dept dept : depts)
		{
			System.out.println("部门信息：" + dept);
		}
	}
}

