package com.dantefung.c3p0_dbutils_basedao;

import java.util.List;

public class TestDao {
	public static void main(String[] args) {
		StudentDao stuDao = new StudentDao();
		List<Students> list = stuDao.findAll();
		for (Students student : list) {
			System.out.println(student);
		}
		
		
		TeacherDao teaDao = new TeacherDao();
		
		List<Teachers> list2 = teaDao.findAll();
		for (Teachers t : list2) {
			System.out.println(t);
		}
	}
}
