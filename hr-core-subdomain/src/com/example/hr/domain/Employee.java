package com.example.hr.domain;

import java.util.List;
// DDD (Domain-Driven Design)
// 1. Strategic Patterns: 
//                                         Analysis                Design                    Architectural Design/Implementation
//                               1    1.*                1      1                  1    1.*
//    i) Bounded-Context: Domain --------> Sub-domain(s) --------> Bounded-Context --------> Microservices
//   ii) Ubiquitous Language: TcKimlikNo, FullName, Money, Iban, Department, JobStyle, BirthYear, Photo  
// 2. Tactical Patterns
//   Entity, Aggregate, Value Object (final/record/enum)

import com.example.ddd.DomainEntity;

// Entity Class: i) identity ii) persistent iii) mutable iv) business methods
@DomainEntity(id="identity")
public class Employee {
	private TcKimlikNo identity;
	private FullName firstName;
	private Money salary;
	private Iban iban;
	private List<Department> departments;
	private JobStyle jobStyle;
	private BirthYear birthYear;
	private Photo photo;
	
	
}
