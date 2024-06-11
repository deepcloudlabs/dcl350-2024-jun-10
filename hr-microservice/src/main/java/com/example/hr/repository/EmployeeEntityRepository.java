package com.example.hr.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hr.entity.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, String> {
	Collection<EmployeeEntity> findFirst10ByLastName(String lastname);
	EmployeeEntity findTopByOrderByBirthYearAsc();
	EmployeeEntity findTopByOrderBySalaryDesc();
//	@Query("select e from EmployeeEntity e where :departments in (e.departments)")
//	Collection<EmployeeEntity> getir(List<String> departments);
	Collection<EmployeeEntity> findAllByDepartmentsIn(List<String> departments);
	
}
