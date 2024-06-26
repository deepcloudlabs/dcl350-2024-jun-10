package com.example.hr.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String> {
	Collection<EmployeeEntity> findFirst10ByLastName(String lastname);
	EmployeeEntity findTopByOrderByBirthYearAsc();
	EmployeeEntity findTopByOrderBySalaryDesc();
	@Query("select e from EmployeeEntity e where :department member of e.departments")
	Collection<EmployeeEntity> getir(String department);
	Collection<EmployeeEntity> findAllByDepartmentsIn(List<String> departments);	
}
