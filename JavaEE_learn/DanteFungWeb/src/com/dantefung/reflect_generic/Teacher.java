package com.dantefung.reflect_generic;

public class Teacher {
	private String id;
	private String name;
	private String salary;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", salary=" + salary
				+ "]";
	}
	
}
