package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.FireEmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.dto.response.PhotoResponse;
import com.example.hr.service.HrService;

@RestController
@RequestScope
@RequestMapping("/employees")
@Validated
@CrossOrigin
public class HrRestController {
	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	// GET http://localhost:7100/hr/api/v1/employees/11111111110
	@GetMapping("{identity}")
	public EmployeeResponse findById(@PathVariable String identity){
		return hrService.findEmployeeByIdentity(identity);
	}
	
	// GET http://localhost:7100/hr/api/v1/employees/11111111110/photo
	@GetMapping("{identity}/photo")
	public PhotoResponse getEmployeePhotoById(@PathVariable String identity){
		return hrService.getEmployeePhoto(identity);
	}
	
	// POST http://localhost:7100/hr/api/v1/employees
	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	
	// DELETE http://localhost:7100/hr/api/v1/employees/11111111110
	@DeleteMapping("{identity}")
	public FireEmployeeResponse fireEmployee(@PathVariable String identity){
		return hrService.fireEmployee(identity);		
	}
}
