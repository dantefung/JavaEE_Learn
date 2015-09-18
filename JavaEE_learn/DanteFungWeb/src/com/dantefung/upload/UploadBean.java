package com.dantefung.upload;

import java.io.Serializable;

/*
 *  描述信息
 *                   文件名
 *                   文件大小
 *                   文件的类型
 *                   文件的存放路径
 *                   文件的存放时间
 *                   文件内容
 */
public class UploadBean implements Serializable{
	  private String name;
      private String size;
	  private String contentType;
	  private String path;
	  private String time;
	  private String content;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "UploadBean [name=" + name + ", size=" + size + ", contentType="
				+ contentType + ", path=" + path + ", time=" + time
				+ ", content=" + content + "]";
	}
	public UploadBean(String name, String size, String contentType,
			String path, String time, String content) {
		super();
		this.name = name;
		this.size = size;
		this.contentType = contentType;
		this.path = path;
		this.time = time;
		this.content = content;
	}
	public UploadBean() {
		super();
	}


	
	
	
	
}
