package com.example.hr.domain;

import java.util.Arrays;
import java.util.List;
// DDD (Domain-Driven Design)
// 1. Strategic Patterns: 
//                                         Analysis                Design                    Architectural Design/Implementation
//                               1    1.*                1      1                  1    1.*
//    i) Bounded-Context: Domain --------> Sub-domain(s) --------> Bounded-Context --------> Microservices
//                                                                                 1   1..*
//                                                                 Bounded-Context --------> Aggregate
//   ii) Ubiquitous Language: TcKimlikNo, FullName, Money, Iban, Department, JobStyle, BirthYear, Photo  
// 2. Tactical Patterns
//   Entity, Aggregate, Value Object (final/record/enum)
import java.util.Objects;

import com.example.ddd.DomainEntity;

// Entity Class: i) identity ii) persistent iii) mutable -> business method iv) business methods
@DomainEntity(id = "identity", aggregate = true)
public class Employee {

	private final TcKimlikNo identity;
	private FullName fullName;
	private Money salary;
	private Iban iban;
	private List<Department> departments;
	private JobStyle jobStyle;
	private final BirthYear birthYear;
	private Photo photo;

	private Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullName = builder.fullName;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.departments = builder.departments;
		this.jobStyle = builder.jobStyle;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
	}

	public static class Builder {
		private final TcKimlikNo identity;
		private FullName fullName;
		private Money salary;
		private Iban iban;
		private List<Department> departments;
		private JobStyle jobStyle;
		private BirthYear birthYear;
		private Photo photo;

		public Builder(String identity) {
			this.identity = TcKimlikNo.valueOf(identity);
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.of(firstName, lastName);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.valueOf(value, currency);
			return this;
		}

		public Builder salary(double value) {
			return salary(value, FiatCurrency.TL);
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder departments(String... values) {
			this.departments = Arrays.stream(values).map(Department::valueOf).toList();
			return this;
		}

		public Builder departments(Department... values) {
			this.departments = Arrays.asList(values);
			return this;
		}

		public Builder jobStyle(JobStyle value) {
			this.jobStyle = value;
			return this;
		}

		public Builder photo(String values) {
			this.photo = Photo.of(values);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.of(values);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = new BirthYear(value);
			return this;
		}

		public Employee build() {
			// Business Rule
			// Validation Rule
			// Constraint
			// Invariants
			// Standards
			// Regulations
			return new Employee(this);
		}

	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

	public FullName getFullName() {
		return fullName;
	}

	public Money getSalary() {
		return salary;
	}

	public Iban getIban() {
		return iban;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(identity, other.identity);
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullName=" + fullName + ", salary=" + salary + ", iban=" + iban
				+ ", departments=" + departments + ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + ", photo="
				+ photo + "]";
	}

}
