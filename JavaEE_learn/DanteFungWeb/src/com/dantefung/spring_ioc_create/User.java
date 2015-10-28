package com.dantefung.spring_ioc_create;

public class User {
	
	private String id;
	private String name;
	
	public User() {
		System.out.println("User.User()");
	}

	public User(String id, String name) {
		super();
		System.out.println("User.User(id,name)");
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
