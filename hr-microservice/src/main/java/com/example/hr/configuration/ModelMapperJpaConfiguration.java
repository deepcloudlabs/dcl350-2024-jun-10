package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
import com.example.hr.domain.JobStyle;
import com.example.hr.domain.Money;
import com.example.hr.dto.response.EmployeeQLResponse;
import com.example.hr.entity.EmployeeEntity;

import jakarta.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(name="persistenceStrategy", havingValue = "jpa")
public class ModelMapperJpaConfiguration {
	private final ModelMapper modelMapper;
	
	public ModelMapperJpaConfiguration(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	private static final Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER =
		context -> {
			var employee = context.getSource();
			var entity = new EmployeeEntity();
			entity.setIdentity(employee.getIdentity().getValue());
			FullName fullName = employee.getFullName();
			Money salary = employee.getSalary();
			entity.setFirstName(fullName.firstName());
			entity.setLastName(fullName.lastName());
			entity.setSalary(salary.value());
			entity.setCurrency(salary.currency());
			entity.setIban(employee.getIban().getValue());
			entity.setBirthYear(employee.getBirthYear().value());
			entity.setDepartments(employee.getDepartments().stream().map(Department::name).toList());
			entity.setJobStyle(employee.getJobStyle().name());
			entity.setPhoto(employee.getPhoto().getValues());
			return entity;
		};
			
			
		private static final Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER =
			context -> {
				var entity = context.getSource();
				return new Employee.Builder(entity.getIdentity())
						           .fullName(entity.getFirstName(), entity.getLastName())
						           .iban(entity.getIban())
						           .salary(entity.getSalary(), entity.getCurrency())
						           .departments(entity.getDepartments().toArray(new String[0]))
						           .jobStyle(JobStyle.valueOf(entity.getJobStyle()))
						           .birthYear(entity.getBirthYear())
						           .photo(entity.getPhoto())
						           .build();
			};		

		private static final Converter<EmployeeEntity,EmployeeQLResponse> EMPLOYEE_ENTITY_TO_EMPLOYEE_QL_RESPONSE_CONVERTER =
		context -> {
			var employee = context.getSource();
			var response = new EmployeeQLResponse();
			response.setIdentity(employee.getIdentity());
			response.setFirstName(employee.getFirstName());
			response.setLastName(employee.getLastName());
			response.setSalary(employee.getSalary());
			response.setCurrency(employee.getCurrency());
			response.setIban(employee.getIban());
			response.setBirthYear(employee.getBirthYear());
			response.setDepartments(employee.getDepartments());
			response.setJobStyle(employee.getJobStyle());
			response.setPhoto(employee.getPhoto());
			return response;
		};	
			
			
	@PostConstruct
	public void addModelMapperConveters() {
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER,Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER,EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_QL_RESPONSE_CONVERTER,EmployeeEntity.class, EmployeeQLResponse.class);
	}
}
