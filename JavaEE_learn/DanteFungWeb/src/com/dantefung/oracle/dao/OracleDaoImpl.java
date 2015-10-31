package com.dantefung.oracle.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDaoImpl {
	
	private static String driver="oracle.jdbc.OracleDriver";
	private static String user="user_37";
	private static String pwd="root";
	private static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	
	//测试oracle使用
	public static void selectList(){
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
			prepareStatement = connection.prepareStatement("select * from student");
			rs = prepareStatement.executeQuery();
			while (rs.next()) {
				System.out.println("学生姓名："+rs.getString("stu_name"));				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(prepareStatement!=null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//测试oracle存储过程使用
	public static void addStudent(){
		Connection connection = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
			call = connection.prepareCall("{call pro_add_student3(?,?,?,?,?,?)}") ;
			call.setString(1,"a111");
			call.setString(2, "张11");
			call.setString(3, "442122333344");
			call.setInt(4, 20);
			call.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
			call.registerOutParameter(6,  oracle.jdbc.OracleTypes.VARCHAR);
			call.execute();
			Integer sFlag = call.getInt(5);
			String sMsg = call.getString(6);
			System.out.println("返回码："+sFlag+",返回信息："+sMsg);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(call!=null){
				try {
					call.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//调用存储函数（有一输入参数和返回值）
	public static void getStuAge(){
		Connection connection = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
			//返回值用？号来接收
			call = connection.prepareCall("{?=call fun_get_age(?)}") ;
			//第一个？号是返回值，需要注册数据类型
			call.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			call.setString(2, "a002");		
			call.execute();
			//接收返回值，返回值顺序为1
			Integer sAge = call.getInt(1);
			System.out.println("学生年龄："+sAge);			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(call!=null){
				try {
					call.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	//调用存储函数（有输入输出参数和返回值）
	public static void getStuAvgAge(){
		Connection connection = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
			//返回值用？号来接收
			call = connection.prepareCall("{?=call fun_get_avg_age(?,?,?)}") ;
			//第一个？号是返回值，需要注册数据类型
			call.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			call.setString(2, "a002");
			call.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
			call.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			call.execute();
			//接收返回值，返回值顺序为1
			Double sAvgAge = call.getDouble(1);
			String sName = call.getString(3);
			Integer sAge = call.getInt(4);
			System.out.println("学生姓名："+sName+",学生年龄："+sAge+",平均年龄："+sAvgAge);		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(call!=null){
				try {
					call.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	
	//调用包中的存储过程（有输入输出参数）
	public static void addStudentByPac(){
		Connection connection = null;
		CallableStatement call = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, pwd);
			//调用存储过程，需要加上包名
			call = connection.prepareCall("{call pac1.pro_add_student(?,?,?,?,?,?)}") ;
			//第一个？号是返回值，需要注册数据类型
			call.setString(1, "a223");
			call.setString(2,"李23");
			call.setString(3,"44177777777x");
			call.setInt(4, 18);
			call.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
			call.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);

			call.execute();
			
			Integer sFlag = call.getInt(5);
			String sMessage = call.getString(6);
			System.out.println("返回码："+sFlag+",返回信息："+sMessage);		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(call!=null){
				try {
					call.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	 public static void main(String[] args) {
		 //selectList();
		 //addStudent();
		 //getStuAge();
		 //getStuAvgAge();
		 addStudentByPac();
	}

}
