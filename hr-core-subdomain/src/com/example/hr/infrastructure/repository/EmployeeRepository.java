package com.example.hr.infrastructure.repository;

import java.util.Optional;

import com.example.hexagonal.Port;
import com.example.hexagonal.PortType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(value = PortType.DRIVEN)
public interface EmployeeRepository {

	Optional<Employee> findById(TcKimlikNo kimlik);

	boolean existsById(TcKimlikNo kimlik);

	Employee persist(Employee employee);
	
	Optional<Employee> remove(Employee employee);

}
