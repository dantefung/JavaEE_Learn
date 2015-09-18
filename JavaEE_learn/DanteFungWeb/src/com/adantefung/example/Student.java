package com.adantefung.example;
/**
 * ѧ��ʵ�����
 * @author APPle
 *
 */
public class Student {
	private String name;//����
	private String idcard;//���֤��
	private String examid;//׼��֤��
	private String location;//��ַ
	private String grade;//�ɼ�
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public Student(String name, String idcard, String examid, String location,
			String grade) {
		super();
		this.name = name;
		this.idcard = idcard;
		this.examid = examid;
		this.location = location;
		this.grade = grade;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [examid=" + examid + ", grade=" + grade + ", idcard="
				+ idcard + ", location=" + location + ", name=" + name + "]";
	}
	
}
