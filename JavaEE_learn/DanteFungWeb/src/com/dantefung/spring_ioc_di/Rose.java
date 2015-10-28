package com.dantefung.spring_ioc_di;

public class Rose {
	
	private Tom tom;
	
	public void setTom(Tom tom) {
		this.tom = tom;
	}
	
	
	public Rose() {
		System.out.println("Rose.Rose()");
	}
}
