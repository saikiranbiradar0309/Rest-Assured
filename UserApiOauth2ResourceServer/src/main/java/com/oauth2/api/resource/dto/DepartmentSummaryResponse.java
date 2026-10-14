package com.oauth2.api.resource.dto;

public class DepartmentSummaryResponse {

	private String department;

	private Long totalEmployees;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Long getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(Long totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

}