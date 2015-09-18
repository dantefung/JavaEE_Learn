package com.dantefung.reflect_generic;

import java.util.List;
/**
 * 测试反射泛型写的通用数据库的基类
 * @author Dante Fung
 *
 */
public class Test {
     
	public static void main(String[] args) {
/*	     StudentDao dao = new StudentDao();
	     List<Student> students = dao.findAll();
	     for(Student stu : students)
	     {
	    	 System.out.println(stu);
	     }*/
	     
	     TeacherDao dao = new TeacherDao();
	     /*List<Teacher> teachers = dao.findAll();
	     for(Teacher t  : teachers)
	     {
	    	 System.out.println(t);
	     }*/
//	     Teacher teacher = dao.findById(1);
//	     System.out.println(teacher);
	     
	     
    }
}
