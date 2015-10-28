package com.dantefung.spring_ioc_helloword;

public class UserDaoImpl implements UserDao{
	
	public UserDaoImpl() {
		System.out.println("UserDaoImpl.UserDaoImpl():\tUserDaoImpl对象创建了！！");
	}

	@Override
	public void save() {
		System.out.println("UserDaoImpl.save()");
	}

}
