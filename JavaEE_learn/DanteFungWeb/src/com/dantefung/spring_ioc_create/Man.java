package com.dantefung.spring_ioc_create;

public class Man {

	private String id;
	private String name;
	
	public Man(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Man() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Man [id=" + id + ", name=" + name + "]";
	}
	
}
