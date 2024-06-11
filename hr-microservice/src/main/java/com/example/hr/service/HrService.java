package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ddd.OpenHostInterface;
import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.PhotoResponse;

@Service
@OpenHostInterface
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;
	
	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	public EmployeeResponse findEmployeeByIdentity(String identity) {
		var employee = hrApplication.findEmployee(TcKimlikNo.valueOf(identity))
				                    .orElseThrow(() -> new IllegalArgumentException("Employee [%s] does not exist.".formatted(identity)));
		// Employee --> EmployeeResponse: Object-to-Object Mapping
		return modelMapper.map(employee, EmployeeResponse.class);
	}

	@Transactional
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		var hiredEmployee = hrApplication.hireEmployee(employee);
		return modelMapper.map(hiredEmployee, HireEmployeeResponse.class);
	}

	@Transactional
	public EmployeeResponse fireEmployee(String identity) {
		var employee = hrApplication.fireEmployee(TcKimlikNo.valueOf(identity))
		                            .orElseThrow(() -> new IllegalArgumentException("Employee [%s] does not exist.".formatted(identity)));
		return null;
	}

	public PhotoResponse getEmployeePhoto(String identity) {
		var employee = hrApplication.findEmployee(TcKimlikNo.valueOf(identity))
                .orElseThrow(() -> new IllegalArgumentException("Employee [%s] does not exist.".formatted(identity)));
        return new PhotoResponse(employee.getPhoto().getBase64Values());
	}

}
