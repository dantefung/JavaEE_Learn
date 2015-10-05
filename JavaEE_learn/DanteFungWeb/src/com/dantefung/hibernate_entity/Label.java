package com.dantefung.hibernate_entity;

public class Label {
	private String id;
	private String lname;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "Label [id=" + id + ", lname=" + lname + "]";
	}
	
	
}
