package com.example.hr.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
import com.example.hr.domain.JobStyle;
import com.example.hr.domain.Money;
import com.example.hr.dto.response.EmployeeQLResponse;

import jakarta.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(name="persistenceStratgey", havingValue = "mongodb")
public class ModelMapperMongoConfiguration {
	private final ModelMapper modelMapper;
	
	public ModelMapperMongoConfiguration(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	private static final Converter<Employee,EmployeeDocument> EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER =
		context -> {
			var employee = context.getSource();
			var document = new EmployeeDocument();
			document.setIdentity(employee.getIdentity().getValue());
			FullName fullName = employee.getFullName();
			Money salary = employee.getSalary();
			document.setFirstName(fullName.firstName());
			document.setLastName(fullName.lastName());
			document.setSalary(salary.value());
			document.setCurrency(salary.currency());
			document.setIban(employee.getIban().getValue());
			document.setBirthYear(employee.getBirthYear().value());
			document.setDepartments(employee.getDepartments().stream().map(Department::name).toList());
			document.setJobStyle(employee.getJobStyle().name());
			document.setPhoto(employee.getPhoto().getBase64Values());
			return document;
		};
			
			
		private static final Converter<EmployeeDocument,Employee> EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER =
			context -> {
				var document = context.getSource();
				return new Employee.Builder(document.getIdentity())
						           .fullName(document.getFirstName(), document.getLastName())
						           .iban(document.getIban())
						           .salary(document.getSalary(), document.getCurrency())
						           .departments(document.getDepartments().toArray(new String[0]))
						           .jobStyle(JobStyle.valueOf(document.getJobStyle()))
						           .birthYear(document.getBirthYear())
						           .photo(document.getPhoto())
						           .build();
			};		

			private static final Converter<EmployeeDocument,EmployeeQLResponse> EMPLOYEE_DOCUMENT_TO_EMPLOYEE_QL_RESPONSE_CONVERTER =
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
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER,Employee.class, EmployeeDocument.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER,EmployeeDocument.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_QL_RESPONSE_CONVERTER,EmployeeDocument.class, EmployeeQLResponse.class);
	}
}
