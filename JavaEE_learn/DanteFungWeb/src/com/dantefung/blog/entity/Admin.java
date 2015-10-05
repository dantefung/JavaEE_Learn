package com.dantefung.blog.entity;

public class Admin {
	private String id;// VARCHAR(32) PRIMARY KEY,
	private String aname;// VARCHAR(20),
	private String apassword;// VARCHAR(20)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", aname=" + aname + ", apassword="
				+ apassword + "]";
	}
	
	public Admin(String id, String aname, String apassword) {
		super();
		this.id = id;
		this.aname = aname;
		this.apassword = apassword;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
