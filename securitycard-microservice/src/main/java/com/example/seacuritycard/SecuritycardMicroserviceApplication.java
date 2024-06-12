package com.example.seacuritycard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SecuritycardMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritycardMicroserviceApplication.class, args);
	}

}
