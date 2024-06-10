package com.example.hr.domain;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.isNull;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.example.ddd.ValueObject;

// Value Object: i) identity -> state ii) immutable class
@ValueObject
public final class TcKimlikNo {
	private final String value;
	private static final Map<String,TcKimlikNo> cache = new ConcurrentHashMap<>();
	
	private TcKimlikNo(String value) {
		this.value = value;
	}

	public static TcKimlikNo valueOf(String value) {
		// validation
		requireNonNull(value);
		requireTcKimlikNo(value);
		// caching
		var kimlik = cache.get(value);
		if (isNull(kimlik)) {
			kimlik = new TcKimlikNo(value);
			cache.put(value, kimlik);
		}
		return kimlik; 
	}

	public static String requireTcKimlikNo(String value) {
		if (!isValid(value))
			throw new IllegalArgumentException("%s is not a valid TC Kimlik No.".formatted(value));
		return value;
	}

	public static boolean isValid(String value) {
		if (value == null)
			return false;
		if (!value.matches("^\\d{11}$")) { // fail-fast
			return false;
		}
		int[] digits = new int[11];
		for (int i = 0; i < digits.length; ++i) {
			digits[i] = value.charAt(i) - '0';
		}
		int x = digits[0];
		int y = digits[1];
		for (int i = 1; i < 5; i++) {
			x += digits[2 * i];
		}
		for (int i = 2; i <= 4; i++) {
			y += digits[2 * i - 1];
		}
		int c1 = 7 * x - y;
		if (c1 % 10 != digits[9]) {
			return false;
		}
		int c2 = 0;
		for (int i = 0; i < 10; ++i) {
			c2 += digits[i];
		}
		if (c2 % 10 != digits[10]) {
			return false;
		}
		return true;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TcKimlikNo other = (TcKimlikNo) obj;
		return Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "TcKimlikNo [value=" + value + "]";
	}

}
