package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
import com.example.hr.domain.JobStyle;
import com.example.hr.domain.Money;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeQLResponse;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

import jakarta.annotation.PostConstruct;

@Configuration
public class ModelMapperWebConfiguration {
	private final ModelMapper modelMapper;
	
	public ModelMapperWebConfiguration(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	private static final Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER =
			context -> {
				var employee = context.getSource();
				var response = new EmployeeResponse();
				response.setIdentity(employee.getIdentity().getValue());
				FullName fullName = employee.getFullName();
				Money salary = employee.getSalary();
				response.setFirstName(fullName.firstName());
				response.setLastName(fullName.lastName());
				response.setSalary(salary.value());
				response.setCurrency(salary.currency());
				response.setIban(employee.getIban().getValue());
				response.setBirthYear(employee.getBirthYear().value());
				response.setDepartments(employee.getDepartments().stream().map(Department::name).toList());
				response.setJobStyle(employee.getJobStyle().name());
				return response;
			};

	private static final Converter<Employee,HireEmployeeResponse> EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER =
			context -> {
				var employee = context.getSource();
				var response = new HireEmployeeResponse();
				response.setIdentity(employee.getIdentity().getValue());
				FullName fullName = employee.getFullName();
				Money salary = employee.getSalary();
				response.setFirstName(fullName.firstName());
				response.setLastName(fullName.lastName());
				response.setSalary(salary.value());
				response.setCurrency(salary.currency());
				response.setIban(employee.getIban().getValue());
				response.setBirthYear(employee.getBirthYear().value());
				response.setDepartments(employee.getDepartments().stream().map(Department::name).toList());
				response.setJobStyle(employee.getJobStyle().name());
				response.setPhoto(employee.getPhoto().getBase64Values());
				return response;
			};
			
	private static final Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER =
			context -> {
				var request = context.getSource();
				return new Employee.Builder(request.getIdentity())
						           .fullName(request.getFirstName(), request.getLastName())
						           .iban(request.getIban())
						           .salary(request.getSalary(), request.getCurrency())
						           .departments(request.getDepartments().toArray(new String[0]))
						           .jobStyle(JobStyle.valueOf(request.getJobStyle()))
						           .birthYear(request.getBirthYear())
						           .photo(request.getPhoto())
						           .build();
			};
					


	private static final Converter<Employee,EmployeeQLResponse> EMPLOYEE_TO_EMPLOYEE_QL_RESPONSE_CONVERTER =
	context -> {
		var employee = context.getSource();
		var response = new EmployeeQLResponse();
		response.setIdentity(employee.getIdentity().getValue());
		response.setFirstName(employee.getFullName().firstName());
		response.setLastName(employee.getFullName().lastName());
		response.setSalary(employee.getSalary().value());
		response.setCurrency(employee.getSalary().currency());
		response.setIban(employee.getIban().getValue());
		response.setBirthYear(employee.getBirthYear().value());
		response.setDepartments(employee.getDepartments().stream().map(Department::name).toList());
		response.setJobStyle(employee.getJobStyle().name());
		response.setPhoto(employee.getPhoto().getBase64Values());
		return response;
	};				
			
	@PostConstruct
	void addModelMapperConverters() {
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER,Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER,HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_HIRE_EMPLOYEE_RESPONSE_CONVERTER,Employee.class, HireEmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_QL_RESPONSE_CONVERTER,Employee.class, EmployeeQLResponse.class);
	}
}
