package com.example.hr.domain;

import static java.util.Objects.requireNonNull;

import com.example.ddd.ValueObject;

@ValueObject
public record FullName(String firstName, String lastName) {

	public static FullName of(String firstName, String lastName) {
		requireNonNull(firstName);
		requireNonNull(lastName);
		return new FullName(firstName, lastName);
	}
}
