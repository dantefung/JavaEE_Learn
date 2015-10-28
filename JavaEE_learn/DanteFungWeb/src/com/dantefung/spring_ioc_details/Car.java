package com.dantefung.spring_ioc_details;

import com.sun.org.apache.xml.internal.security.Init;

public class Car {
	
	public Car() {
		System.out.println("Car.Car()");
	}
	
	public void init() {
		System.out.println("Car.init()");
	}
	
	public void destroy() {
		System.out.println("Car.destroy()");
	}
	
	
}
