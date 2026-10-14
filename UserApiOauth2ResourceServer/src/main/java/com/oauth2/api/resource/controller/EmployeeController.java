package com.oauth2.api.resource.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oauth2.api.resource.entity.Employee;
import com.oauth2.api.resource.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RequestMapping("/employees")
@Tag(name="Employee APIs")
@RestController
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Operation(summary="Get All Employees")
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {

		return ResponseEntity.ok(employeeService.findAll());
	}
	
	@Operation(summary="Get Employees as id")
	@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {

		return ResponseEntity.ok(employeeService.findById(id));
	}

	@Operation(summary="Create Employee")

	@SecurityRequirement(name="BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

		return new ResponseEntity<>(employeeService.save(employee), HttpStatus.CREATED);
	}

	@Operation(summary="Update Employee")

	@SecurityRequirement(name="BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {

		return ResponseEntity.ok(employeeService.update(id, employee));
	}

	@Operation(summary="Delete Employee")

	@SecurityRequirement(name="BearerAuth")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.delete(id);

		return ResponseEntity.noContent().build();
	}
}