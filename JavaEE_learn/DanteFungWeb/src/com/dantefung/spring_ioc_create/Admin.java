package com.dantefung.spring_ioc_create;

public class Admin {
	
	private String id;
	private String name;
	
	public Admin(String id, String name) {
		super();
		System.out.println("Admin.Admin(id,name)");
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + "]";
	}

	
}
