package com.imsi.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarApplication {

	public static void main(String[] args) {
		System.out.println("Hello world");
		SpringApplication.run(CarApplication.class, args);
	}

}
