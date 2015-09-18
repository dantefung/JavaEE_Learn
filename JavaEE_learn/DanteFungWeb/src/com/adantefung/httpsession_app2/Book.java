package com.adantefung.httpsession_app2;
/**
 * 实体类
 * @author Dante Fung
 *
 */
public class Book {
	private String name;

	private String price;
	
	private String author;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", price=" + price + ", author=" + author
				+ "]";
	}

	public Book(String name, String price, String author) {
		super();
		this.name = name;
		this.price = price;
		this.author = author;
	}

	public Book() {
		super();
	}
	
	
}
