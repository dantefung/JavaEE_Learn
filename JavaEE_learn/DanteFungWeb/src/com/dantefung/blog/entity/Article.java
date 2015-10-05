package com.dantefung.blog.entity;

import java.util.HashSet;
import java.util.Set;

public class Article {
	private String aid;// VARCHAR(32) PRIMARY KEY,-- UUID
	private String title;// VARCHAR(100),
	private String cid;// VARCHAR(32), -- 文章所属的类别
	private String adate;// DATETIME, -- 发表时间
	private String digest;// VARCHAR(300), -- 摘要
	private String content;// LONGTEXT, -- 内容
	private int acount;// INT, -- 浏览次数
	private String atimestamp;// TIMESTAMP, -- 更新时间
	private String label;// VARCHAR(100), -- 标签
	private Category category;
	private Set<Comments> comments = new HashSet<Comments>();
	private Set<Label> labels = new HashSet<Label>();
	
	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Comments> getComments() {
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getAdate() {
		return adate;
	}

	public void setAdate(String adate) {
		this.adate = adate;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAcount() {
		return acount;
	}

	public void setAcount(int acount) {
		this.acount = acount;
	}

	public String getAtimestamp() {
		return atimestamp;
	}

	public void setAtimestamp(String atimestamp) {
		this.atimestamp = atimestamp;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "Article [aid=" + aid + ", title=" + title + ", cid=" + cid
				+ ", adate=" + adate + ", digest=" + digest + ", content="
				+ content + ", acount=" + acount + ", atimestamp=" + atimestamp
				+ ", label=" + label + "]";
	}

	public Article(String aid, String title, String cid, String adate,
			String digest, String content, int acount, String atimestamp,
			String label) {
		super();
		this.aid = aid;
		this.title = title;
		this.cid = cid;
		this.adate = adate;
		this.digest = digest;
		this.content = content;
		this.acount = acount;
		this.atimestamp = atimestamp;
		this.label = label;
	}

	public Article() {
		super();
	}

	
}
