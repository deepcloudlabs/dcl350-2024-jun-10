package com.example.hr.entity;

import java.util.List;
import java.util.Objects;

import com.example.hr.domain.FiatCurrency;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class EmployeeEntity {
	@Id
	@TcKimlikNo
	private String identity;
	@Column(name="fname")
	private String firstName;
	@Column(name="lname")
	private String lastName;
	@Column(name="maas")
	private double salary;
	@Enumerated(EnumType.ORDINAL)
	private FiatCurrency currency;
	@Iban
	private String iban;
	@ElementCollection
	private List<String> departments;
	private String jobStyle;
	private int birthYear;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
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
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
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
		EmployeeEntity other = (EmployeeEntity) obj;
		return Objects.equals(identity, other.identity);
	}
	@Override
	public String toString() {
		return "EmployeeEntity [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + ", currency=" + currency + ", iban=" + iban + ", departments=" + departments
				+ ", jobStyle=" + jobStyle + ", birthYear=" + birthYear + "]";
	}
	
	
}
