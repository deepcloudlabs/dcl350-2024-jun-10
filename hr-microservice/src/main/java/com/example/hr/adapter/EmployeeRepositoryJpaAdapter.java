package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hexagonal.Adapter;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.infrastructure.repository.EmployeeRepository;
import com.example.hr.repository.EmployeeEntityRepository;

@Repository
@Adapter(port = EmployeeRepository.class)
@ConditionalOnProperty(name="persistenceStratgey", havingValue = "jpa")
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;

	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Optional<Employee> findById(TcKimlikNo kimlik) {
		return employeeEntityRepository.findById(kimlik.getValue())
				.map(entity -> modelMapper.map(entity, Employee.class));
	}

	@Override
	public boolean existsById(TcKimlikNo kimlik) {
		return employeeEntityRepository.existsById(kimlik.getValue());
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Employee persist(Employee employee) {
		var entity = modelMapper.map(employee, EmployeeEntity.class);
		return modelMapper.map(employeeEntityRepository.save(entity), Employee.class);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.MANDATORY)
	public Optional<Employee> remove(Employee employee) {
		var identity = employee.getIdentity().getValue();
		var entity = employeeEntityRepository.findById(identity);
		entity.ifPresent(employeeEntityRepository::delete);
		return entity.map(employeeEntity -> modelMapper.map(employeeEntity, Employee.class));
	}

}
