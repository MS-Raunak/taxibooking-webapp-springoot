package com.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxibookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxibookingApplication.class, args);
		System.out.println("Server Started On Port 8080");
	}

}
