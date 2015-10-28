package com.dantefung.spring_ioc_di;

import org.springframework.stereotype.Component;

@Component
public class Boat {
	
	public Boat() {
			System.out.println("Boat.Boat()");
	}

	@Override
	public String toString() {
		return "Boat [xxxx]";
	}
	
	
}
