package com.oauth2.api.resource.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oauth2.api.resource.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByDepartment(String department);

	boolean existsByEmail(String email);

	@Query("""
			SELECT COUNT(DISTINCT e.department)
			FROM Employee e
			""")
	Long totalDepartments();

	@Query("""
			SELECT AVG(e.salary)
			FROM Employee e
			""")
	Double averageSalary();

	@Query("""
			SELECT SUM(e.salary)
			FROM Employee e
			""")
	Double totalSalary();

	@Query("""
			SELECT
			     e.department,
			     COUNT(e)
			FROM Employee e
			GROUP BY e.department
			ORDER BY COUNT(e) DESC
			""")
	List<Object[]> departmentSummary();

	@Query("""
			SELECT
			     e.department,
			     SUM(e.salary)
			FROM Employee e
			GROUP BY e.department
			ORDER BY SUM(e.salary) DESC
			""")
	List<Object[]> salarySummary();


	@Query("""
			SELECT
			FUNCTION('DATE_FORMAT', e.createdAt, '%b %Y'),
			COUNT(e)
			FROM Employee e
			GROUP BY
			FUNCTION('YEAR', e.createdAt),
			FUNCTION('MONTH', e.createdAt),
			FUNCTION('DATE_FORMAT', e.createdAt, '%b %Y')
			ORDER BY
			FUNCTION('YEAR', e.createdAt),
			FUNCTION('MONTH', e.createdAt)
			""")
	List<Object[]> employeeGrowth();

}