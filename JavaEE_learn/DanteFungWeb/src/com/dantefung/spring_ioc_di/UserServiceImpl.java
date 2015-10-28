package com.dantefung.spring_ioc_di;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserServiceImpl() {
		System.out.println("UserServiceImpl.UserServiceImpl()");
	}
}
