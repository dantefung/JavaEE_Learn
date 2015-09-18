package com.dantefung.reflect_generic;

public class Student {
	
	/*
	 * CREATE TABLE student(
id INT PRIMARY KEY AUTO_INCREMENT,
NAME VARCHAR(20) NOT NULL,
chinese FLOAT NOT NULL,
english FLOAT NOT NULL,
math FLOAT NOT NULL
);
	 */
	
	private String id;
	private String name;
	private String chinese;
	private String english;
	private String math;
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public void setMath(String math) {
		this.math = math;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", chinese=" + chinese
				+ ", english=" + english + ", math=" + math + "]";
	}
	

}
