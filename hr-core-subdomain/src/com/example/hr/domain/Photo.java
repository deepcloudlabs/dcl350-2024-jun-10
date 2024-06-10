package com.example.hr.domain;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Base64;

import com.example.ddd.ValueObject;

@ValueObject
public final class Photo {
	private final byte[] values;

	private Photo(byte[] values) {
		this.values = values;
	}

	public byte[] getValues() {
		return values;
	}

	public String getBase64Values() {
		return Base64.getEncoder().encodeToString(values);
	}

	public static Photo of(byte[] values) {
		requireNonNull(values);
		return new Photo(values);
	}
	
	public static Photo of(String values) {
		requireNonNull(values);
		return new Photo(Base64.getDecoder().decode(values));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		return Arrays.equals(values, other.values);
	}

	@Override
	public String toString() {
		return "data:image/png;base64,".concat(this.getBase64Values());
	}

}
