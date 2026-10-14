package com.oauth2.api.resource.dto;

public class SalarySummaryResponse {

	private String department;

	private Double totalSalary;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Double getTotalSalary() {
		return totalSalary;
	}

	public void setTotalSalary(Double totalSalary) {
		this.totalSalary = totalSalary;
	}

}