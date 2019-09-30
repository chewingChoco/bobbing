package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages= {"com.example.demo.persistence, com.example.demo.controller"})
public class BabbingApplication{

	public static void main(String[] args) {
		SpringApplication.run(BabbingApplication.class, args);
	}


	

}
