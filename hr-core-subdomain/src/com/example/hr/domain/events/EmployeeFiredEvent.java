package com.example.hr.domain.events;

import com.example.hr.domain.Employee;

public class EmployeeFiredEvent extends HrEvent {

	private final Employee firedEmployee;

	public EmployeeFiredEvent(Employee firedEmployee) {
		super(firedEmployee.getIdentity(), HrEventType.EMPLOYEE_FIRED);
		this.firedEmployee = firedEmployee;
	}

	public Employee getFiredEmployee() {
		return firedEmployee;
	}

}
