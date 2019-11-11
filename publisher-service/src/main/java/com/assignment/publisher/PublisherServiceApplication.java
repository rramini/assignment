package com.assignment.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.assignment.publisher" })
public class PublisherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherServiceApplication.class, args);
	}

}
