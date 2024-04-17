package com.sum.SpringJDBC;

import com.sum.SpringJDBC.repo.AlienRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringJdbcApplication.class, args);

		Alien alien = context.getBean(Alien.class);
		alien.setId(1);
		alien.setName("Madhusha");
		alien.setTech("Java");

		AlienRepo alienRepo = context.getBean(AlienRepo.class);
		alienRepo.save(alien);

		System.out.println(alienRepo.findAll());
	}

}
