package com.example.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HrConsumerSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrConsumerSpringBootApplication.class, args);
	}

}
