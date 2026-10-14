package com.oauth2.api.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DashboardStatisticsResponse {

	private Long totalEmployees;

	private Long totalDepartments;

	private Double averageSalary;

	private Double totalSalary;

	public Long getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(Long totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

	public Long getTotalDepartments() {
		return totalDepartments;
	}

	public void setTotalDepartments(Long totalDepartments) {
		this.totalDepartments = totalDepartments;
	}

	public Double getAverageSalary() {
		return averageSalary;
	}

	public void setAverageSalary(Double averageSalary) {
		this.averageSalary = averageSalary;
	}

	public Double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(Double totalSalary) {
		this.totalSalary = totalSalary;
	}

}