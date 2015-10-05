package com.dantefung.blog.entity;

import java.util.List;

public class Category {
	private String cid;// VARCHAR(32) PRIMARY KEY ,
	private String cname;// VARCHAR(30),
	private String descr;// VARCHAR(100)
	private List<Article> articles;
	
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", descr=" + descr
				+ "]";
	}
	
	
	
}
