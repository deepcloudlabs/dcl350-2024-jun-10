package com.example.hr.controller;

import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.hr.dto.response.EmployeeQLResponse;
import com.example.hr.repository.EmployeeEntityRepository;

@Controller
public class EmployeeController {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeController(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@QueryMapping
	public EmployeeQLResponse employeeById(@Argument String identity) {
		var entity = employeeEntityRepository.findById(identity).orElseThrow();
		return modelMapper.map(entity,EmployeeQLResponse.class);
	}
}
