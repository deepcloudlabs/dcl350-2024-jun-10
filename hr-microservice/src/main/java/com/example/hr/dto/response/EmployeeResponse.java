package com.example.hr.dto.response;

import java.util.List;

import com.example.ddd.DataTransferObject;
import com.example.ddd.TransferType;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;

@DataTransferObject(value=Employee.class,type = TransferType.RESPONSE)
public class EmployeeResponse {
	private String identity;
	private String firstName;
	private String lastName;
	private double salary;
	private FiatCurrency currency;
	private String iban;
	private List<String> departments;
	private String jobStyle;
	private int birthYear;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	public void setCurrency(FiatCurrency currency) {
		this.currency = currency;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}

	public String getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(String jobStyle) {
		this.jobStyle = jobStyle;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		return "EmployeeResponse [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", currency=" + currency + ", iban=" + iban + ", departments=" + departments
				+ ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + "]";
	}

}
