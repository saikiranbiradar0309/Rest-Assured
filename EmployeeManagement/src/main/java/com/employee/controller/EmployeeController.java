package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.service.EmployeeServices;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeServices employeeServices;

	@PostMapping(path = "/api/employees")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {

		Employee savedEmployee = employeeServices.createEmployee(employee);

		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	@GetMapping(path = "/api/employees")
	@Operation(summary = "Get All Employees")
	public List<Employee> getAllEmployee() {
		return employeeServices.getAllEmployees();
	}

	@GetMapping(path = "/api/employee/{id}")
	@Operation(summary = "Get Employee as per id")
	public ResponseEntity<Employee> getEmployeeAsPerId(@PathVariable Long id) {
		Employee employee = employeeServices.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}

	@GetMapping("/api/employees/city/{city}")
	@Operation(description = "Get Employees as per City Name", summary = "Get Employees as per City Name")

	public ResponseEntity<List<Employee>> getEmployeesByCity(@PathVariable String city) {

		List<Employee> employees = employeeServices.getEmployeesByCity(city);

		return ResponseEntity.ok(employees);
	}

	@PutMapping("/api/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee) {
		Employee updatedEmployee = employeeServices.updateEmployee(id, employee);

		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping(path = "/api/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeServices.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}

}
