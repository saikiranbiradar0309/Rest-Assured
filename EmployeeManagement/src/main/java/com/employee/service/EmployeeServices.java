package com.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServices {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	// Get All Employees
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	// Get Employee By Id
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
	}

	public List<Employee> getEmployeesByCity(String city) {

		List<Employee> employees = employeeRepository.findByCity(city);

		if (employees.isEmpty()) {
			throw new RuntimeException("No employees found for city: " + city);
		}

		return employees;
	}

	public Employee updateEmployee(Long id, Employee employeeDetails) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setMobileNumber(employeeDetails.getMobileNumber());
		employee.setCity(employeeDetails.getCity());
		employee.setCountry(employeeDetails.getCountry());
		employee.setProjectName(employeeDetails.getProjectName());
		employee.setUsername(employeeDetails.getUsername());
		employee.setPassword(employeeDetails.getPassword());

		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

		employeeRepository.delete(employee);
	}

}
