package com.sum.springPostgreSQL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringPostgreSqlApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringPostgreSqlApplication.class, args);

		ProductService service = context.getBean(ProductService.class);

		System.out.println(service.getAllProducts());
	}
}