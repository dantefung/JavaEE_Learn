package com.dantefung.hibernate_entity;

public class Comments {
   	private String cmid;// VARCHAR(32) PRIMARY KEY,
   	private String content;// TEXT,-- 访客填写的评论
   	private String cdate;// DATETIME,-- 评论时间
   	private String cname;// VARCHAR(100),-- 游客自己填写的称呼
   	private String aid;// VARCHAR(32),-- 该评论所属的文章
   	private Article article;
   	
   	
   	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getCmid() {
		return cmid;
	}
	public void setCmid(String cmid) {
		this.cmid = cmid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	
	@Override
	public String toString() {
		return "Comments [cmid=" + cmid + ", content=" + content + ", cdate="
				+ cdate + ", cname=" + cname + ", aid=" + aid + "]";
	}
   	
   	
}
