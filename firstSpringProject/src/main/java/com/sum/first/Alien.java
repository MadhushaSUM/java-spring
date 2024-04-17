package com.sum.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Alien {
	
	@Autowired
	private Laptop lap;
	public void Code() {
		System.out.println("I am coding...");
		lap.Compile();
	}

}
