package com.dantefung.spring_ioc_helloword;

public class UserServiceImpl implements UserService{
	
	public UserServiceImpl() {
		System.out.println("UserServiceImpl.UserServiceImpl()：\tUserServiceImpl对象创建了！！");
	}
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		System.out.println("UserServiceImpl.setUserDao(): \tUserDao对象被注入！！");
		this.userDao = userDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
}
