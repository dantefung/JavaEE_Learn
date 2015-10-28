package com.dantefung.spring_ioc_create;

public class Factory {
	
	private String name;
	
	public Factory() {
		System.out.println("Factory.Factory()");
	}
	
	
	public Factory(String name) {
		super();
		System.out.println("Factory.Factory(name)");
		this.name = name;
	}



	// 静态方法
	public Man getInstance() 
	{
		return new Man("2","张三");
	}
	
	// 非静态方法
	public static Man getInstanceByStatic()
	{
		return new Man("3","李四");
	}


	@Override
	public String toString() {
		return "Factory [name=" + name + "]";
	}
	
	
}
