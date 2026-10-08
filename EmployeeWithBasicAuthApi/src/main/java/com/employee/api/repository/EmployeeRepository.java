package com.employee.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.api.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	
}
