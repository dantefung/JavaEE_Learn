package com.dantefung.domain;

public class Student {
	private String name;
	private String gender;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", gender=" + gender + ", age=" + age
				+ "]";
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String name, String gender, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
	

}
