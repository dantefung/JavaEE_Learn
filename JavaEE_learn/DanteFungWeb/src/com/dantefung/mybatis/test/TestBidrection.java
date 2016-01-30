/**
 * Project Name:DanteFungWeb
 * File Name:TestBidrection.java
 * Package Name:com.dantefung.mybatis.test
 * Date:2015-11-26下午8:09:32
 * Copyright (c) 2015, fhlin0611@foxmail.com All Rights Reserved.
 *
*/

package com.dantefung.mybatis.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dantefung.mybatis_bidirection_association.dao.impl.DeptDaoImpl;
import com.dantefung.mybatis_bidirection_association.dao.impl.EmpDaoImpl;
import com.dantefung.mybatis_bidirection_association.entity.Dept;
import com.dantefung.mybatis_bidirection_association.entity.Emp;

/**
 * ClassName:TestBidrection <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015-11-26 下午8:09:32 <br/>
 * @author   Dante Fung
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class TestBidrection
{
	private static DeptDaoImpl deptDaoImpl;
	private static EmpDaoImpl empDaoImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		deptDaoImpl = new DeptDaoImpl();
		empDaoImpl = new EmpDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		deptDaoImpl = null;
		empDaoImpl = null;
	}

	@Test
	public void testSelectDeptEmpList() {
		List<Dept> depts = deptDaoImpl.selectDeptEmpList("%研%");
		for (Dept dept : depts) {
			System.out.println("部门信息："+dept);
		}
	}
	
	@Test
	public void testSelectEmpDeptList() {
		List<Emp> emps = empDaoImpl.selectEmpDeptList("%张%");
		for (Emp emp : emps) {
			System.out.println("员工信息："+emp);
		}
	}
	
}

