package com.example.hr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;
import com.example.hr.infrastructure.repository.EmployeeRepository;

@Configuration
public class ApplicationConfig {

	@Bean
	HrApplication createHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		System.err.println(employeeRepository.getClass().getName());
		System.err.println(eventPublisher.getClass().getName());
		return new StandardHrApplication(employeeRepository,eventPublisher);
	}
}
