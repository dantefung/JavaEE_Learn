package com.dantefung.spring_ioc_di;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
@Component
public class Person {

	@Resource
	private Boat boat;
	
	public Person() {
		System.out.println("Person.Person()");
	}
	
	public Boat getBoat() {
		return boat;
	}
}
