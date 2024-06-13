package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import com.example.hexagonal.Adapter;
import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infrastructure.repository.EmployeeRepository;
import com.example.hr.repository.EmployeeDocumentRepository;

@Repository
@Adapter(port = EmployeeRepository.class)
@ConditionalOnProperty(name="persistenceStrategy", havingValue = "mongodb")
public class EmployeeRepositoryMongoAdapter implements EmployeeRepository {
	private final EmployeeDocumentRepository employeeDocumentRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryMongoAdapter(EmployeeDocumentRepository employeeDocumentRepository, ModelMapper modelMapper) {
		this.employeeDocumentRepository = employeeDocumentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findById(TcKimlikNo kimlik) {
		return employeeDocumentRepository.findById(kimlik.getValue()).map(document -> modelMapper.map(document, Employee.class));
	}

	@Override
	public boolean existsById(TcKimlikNo kimlik) {
		return employeeDocumentRepository.existsById(kimlik.getValue());
	}

	@Override
	public Employee persist(Employee employee) {
		var document  = modelMapper.map(employee, EmployeeDocument.class);
		var insertedDocument = employeeDocumentRepository.insert(document);
		return modelMapper.map(insertedDocument, Employee.class);
	}

	@Override
	public Optional<Employee> remove(Employee employee) {
		var identity = employee.getIdentity().getValue();
		var document = employeeDocumentRepository.findById(identity);
		document.ifPresent(employeeDocumentRepository::delete);
		return document.map(doc -> modelMapper.map(doc, Employee.class));
	}

}
