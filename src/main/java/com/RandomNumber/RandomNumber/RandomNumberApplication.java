package com.RandomNumber.RandomNumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling	
public class RandomNumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomNumberApplication.class, args);
	}

}
