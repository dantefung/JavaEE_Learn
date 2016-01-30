package com.dantefung.mybatis_bidirection_association.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dantefung.mybatis.util.MybatisSessionFactory;
import com.dantefung.mybatis_bidirection_association.entity.Dept;

public class DeptDaoImpl {
	SqlSession session;

	/**
	 * @Title: selectEmpDeptList
	 * @auth:chufeng
	 * @Description: 根据部门名称查询部门信息（包括部门员工信息）
	 * @param @param deptName
	 * @param @return
	 * @return List<Dept> 返回类型
	*/ 
	public List<Dept> selectDeptEmpList(String deptName){
		List<Dept> depts = null;
		try {
			session = MybatisSessionFactory.getSession();
			depts = session.selectList("com.dantefung.mybatis_bidirection_association.entity.DeptMapper.selectDeptEmpList",deptName);
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
		return depts;
		
	}

	
	
}
