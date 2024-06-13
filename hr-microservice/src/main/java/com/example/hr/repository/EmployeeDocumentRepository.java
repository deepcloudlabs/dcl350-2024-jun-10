package com.example.hr.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.hr.document.EmployeeDocument;

public interface EmployeeDocumentRepository extends MongoRepository<EmployeeDocument, String> {
	Collection<EmployeeDocument> findFirst10ByLastName(String lastname);
	EmployeeDocument findTopByOrderByBirthYearAsc();
	EmployeeDocument findTopByOrderBySalaryDesc();
	@Query("{departments: {$in: ?1}}")
	Collection<EmployeeDocument> getir(String department);
	Collection<EmployeeDocument> findAllByDepartmentsIn(List<String> departments);	
}
