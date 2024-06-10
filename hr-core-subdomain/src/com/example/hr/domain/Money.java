package com.example.hr.domain;

import static java.util.Objects.requireNonNull;

import com.example.ddd.ValueObject;

@ValueObject
public record Money(double value, FiatCurrency currency) {

	public static Money valueOf(double value, FiatCurrency currency) {
		requirePositive(value);
		requireNonNull(currency);
		return new Money(value, currency);
	}

	private static double requirePositive(double value) {
		if (value <= 0.0)
			throw new IllegalArgumentException("%f should be positive!".formatted(value));
		return value;
	}
}
