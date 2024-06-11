package com.example.hr.domain.events;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends HrEvent {

	public EmployeeHiredEvent(TcKimlikNo identity) {
		super(identity, HrEventType.EMPLOYEE_HIRED);
	}
	
}
