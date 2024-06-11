package com.example.hr.application.business.exception;

import com.example.ddd.BusinessException;
import com.example.hr.domain.TcKimlikNo;

@BusinessException
public class EmployeeAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final TcKimlikNo identity;

	public EmployeeAlreadyExists(TcKimlikNo identity) {
		super("The employee [%s] already exists.".formatted(identity.getValue()));
		this.identity = identity;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

}
