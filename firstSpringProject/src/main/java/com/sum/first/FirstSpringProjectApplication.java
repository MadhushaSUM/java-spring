package com.sum.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FirstSpringProjectApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FirstSpringProjectApplication.class, args);
		
		Alien alienObj = context.getBean(Alien.class);
		alienObj.Code();
	}

}
