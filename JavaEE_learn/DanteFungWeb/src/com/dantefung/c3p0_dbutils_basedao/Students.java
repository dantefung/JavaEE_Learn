package com.dantefung.c3p0_dbutils_basedao;

@Table(name="t_students")
public class Students {
	@Column(name="t_id")
	private int id;
	
	@Column(name="t_name")
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
}
