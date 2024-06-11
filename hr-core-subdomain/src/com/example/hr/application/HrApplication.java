package com.example.hr.application;

import static com.example.hexagonal.PortType.DRIVING;

import java.util.Optional;

import com.example.hexagonal.Port;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port(DRIVING)
public interface HrApplication {

	Employee hireEmployee(Employee employee);
	
	Optional<Employee> fireEmployee(TcKimlikNo kimlik);
	
	Optional<Employee> findEmployee(TcKimlikNo kimlik);
}
