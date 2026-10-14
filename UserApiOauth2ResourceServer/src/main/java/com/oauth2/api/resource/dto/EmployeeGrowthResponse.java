package com.oauth2.api.resource.dto;

public class EmployeeGrowthResponse {

	private String month;

	private Long totalEmployees;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getTotalEmployees() {
		return totalEmployees;
	}

	public void setTotalEmployees(Long totalEmployees) {
		this.totalEmployees = totalEmployees;
	}

}