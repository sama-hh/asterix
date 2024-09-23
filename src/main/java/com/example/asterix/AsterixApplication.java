package com.example.asterix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AsterixApplication {

	public static void main(String[] args) {

		System.out.println(System.getenv("MONGODB_URI"));

		SpringApplication.run(AsterixApplication.class, args);


	}
}
