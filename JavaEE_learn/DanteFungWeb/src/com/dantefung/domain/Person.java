package com.dantefung.domain;
// notice： getter and setter function must be exist.
public class Person {

	private String name;
	
	private Address address;
	
    public void setName(String name) {
		this.name = name;
	}
    
    public String getName() {
		return name;
	}
    
    public void setAddress(Address address) {
		this.address = address;
	}
    
    public Address getAddress() {
		return address;
	}
    
    public Person(String name) {
    	this.name = name;
    }
    
    public Person()
    {
    	
    }
}
