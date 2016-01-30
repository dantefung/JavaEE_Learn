package com.dantefung.mybatis_2one.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dantefung.mybatis.util.MybatisSessionFactory;
import com.dantefung.mybatis_2one.entity.Emp;

public class DeptDaoImpl
{
	SqlSession session;

	public List<Emp> selectEmpDeptList(String empName)
	{
		List<Emp> emps = null;
		try
		{
			session = MybatisSessionFactory.getSession();
			emps = session.selectList(
					"com.dantefung.mybatis_2one.entity.EmpMapper.selectEmpDeptList", empName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				MybatisSessionFactory.closeSession();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return emps;

	}

}
