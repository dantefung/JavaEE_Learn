package com.dantefung.mybatis_bidirection_association.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dantefung.mybatis.util.MybatisSessionFactory;
import com.dantefung.mybatis_bidirection_association.entity.Emp;


public class EmpDaoImpl {
	SqlSession session;


	public List<Emp> selectEmpDeptList(String empName){
		List<Emp> emps = null;
		try {
			session = MybatisSessionFactory.getSession();
			emps = session.selectList("com.dantefung.mybatis_bidirection_association.entity.EmpMapper.selectEmpDeptList",empName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				MybatisSessionFactory.closeSession();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return emps;
		
	}

	
	
}
