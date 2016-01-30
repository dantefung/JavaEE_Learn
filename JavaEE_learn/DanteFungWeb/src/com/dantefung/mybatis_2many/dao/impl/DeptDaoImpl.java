package com.dantefung.mybatis_2many.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dantefung.mybatis.util.MybatisSessionFactory;
import com.dantefung.mybatis_2many.entity.Dept;

public class DeptDaoImpl
{
	SqlSession session;

	public List<Dept> selectDeptEmpList(String deptName)
	{
		List<Dept> depts = null;
		try
		{
			session = MybatisSessionFactory.getSession();
			System.out.println(session);
			depts = session.selectList(
					"com.dantefung.mybatis_2many.entity.DeptMapper.selectDeptEmpList", deptName);
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
		return depts;

	}

}
