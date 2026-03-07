package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})

public class BankProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankProjectApplication.class, args);
		

	}

}
