package com.example.hr.domain;

import com.example.ddd.ValueObject;

@ValueObject
public enum JobStyle {
	FULL_TIME(100), PART_TIME(200), INTERN(50), FREELANCE(250), CONTRACTOR(400);
	private final int code;

	private JobStyle(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
}
