package com.dantefung.spring_ioc_create;

public class Person {
	
	private String id;
	private String name;
	
	public Person(String id, String name) {
		super();
		System.out.println("Person.Person(id,name)");
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
	
}
