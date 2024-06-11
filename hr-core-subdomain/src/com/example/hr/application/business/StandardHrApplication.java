package com.example.hr.application.business;

import java.util.Optional;

import com.example.ddd.Application;
import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeAlreadyExists;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.domain.events.EmployeeFiredEvent;
import com.example.hr.domain.events.EmployeeHiredEvent;
import com.example.hr.domain.events.HrEvent;
import com.example.hr.infrastructure.messaging.EventPublisher;
import com.example.hr.infrastructure.repository.EmployeeRepository;

@Application(port = HrApplication.class)
public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrEvent> eventPublisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEvent> eventPublisher) {
		this.employeeRepository = employeeRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var kimlik = employee.getIdentity();
		if (employeeRepository.existsById(kimlik))
			throw new EmployeeAlreadyExists(kimlik);
		var persistedEmployee = employeeRepository.persist(employee);
		var event = new EmployeeHiredEvent(employee.getIdentity());
		eventPublisher.publish(event);
		return persistedEmployee;
	}

	@Override
	public Optional<Employee> fireEmployee(TcKimlikNo kimlik) {
		var employee = employeeRepository.findById(kimlik);
		employee.ifPresent( firedEmployee -> {
			employeeRepository.remove(firedEmployee);
			var event = new EmployeeFiredEvent(firedEmployee);
			eventPublisher.publish(event);			
		});
		
		return employee;
	}

	@Override
	public Optional<Employee> findEmployee(TcKimlikNo kimlik) {
		return employeeRepository.findById(kimlik);
	}

}
