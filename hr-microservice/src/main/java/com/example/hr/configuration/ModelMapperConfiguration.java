package com.example.hr.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
	
	@Bean
	ModelMapper createModelMapper() {
		return new ModelMapper();
	}	

}
