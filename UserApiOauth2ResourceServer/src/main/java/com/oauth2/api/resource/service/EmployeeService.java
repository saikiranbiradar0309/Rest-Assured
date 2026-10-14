package com.oauth2.api.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oauth2.api.resource.entity.Employee;
import com.oauth2.api.resource.exception.DuplicateDepartmentException;
import com.oauth2.api.resource.exception.DuplicateEmailException;
import com.oauth2.api.resource.exception.EmployeeNotFoundException;
import com.oauth2.api.resource.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public List<Employee> findAll() {
		return repository.findAll();
	}

	public Employee findById(Long id) {

		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
	}

	public Employee save(Employee employee) {

		if (repository.existsByEmail(employee.getEmail())) {
			throw new DuplicateEmailException();
		}

		return repository.save(employee);
	}

	public Employee update(Long id, Employee employee) {

		Employee existing = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

		if (!existing.getEmail().equals(employee.getEmail()) && repository.existsByEmail(employee.getEmail())) {

			throw new DuplicateEmailException();
		}

		existing.setName(employee.getName());
		existing.setEmail(employee.getEmail());
		existing.setDepartment(employee.getDepartment());
		existing.setSalary(employee.getSalary());

		return repository.save(existing);
	}

	public void delete(Long id) {

		Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));

		repository.delete(employee);
	}
}