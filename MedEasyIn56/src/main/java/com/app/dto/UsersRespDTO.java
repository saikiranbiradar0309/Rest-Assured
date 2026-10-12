package com.app.dto;

import com.app.entities.Role;

public class UsersRespDTO {
//	private Long Id;
	private String firstName;
	private String lastName;
	private String email;

	private Role role;
	private String mobileNumber;

	/*
	 * {"firstName":"Mathuresh","lastName":"Yadav","email":"m@gmail.com","password":
	 * "satyajeet","role":"CUSTOMER","mobileNumber":"1234567890",}
	 */

	public UsersRespDTO() {
		super();
		System.out.println("in ctor " + getClass().getName());
	}

	public UsersRespDTO(String firstName, String lastName, String email, Role role, String mobileNumber) {
		super();
//		this.Id=Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.mobileNumber = mobileNumber;
	}

//	public Long getId() {
//		return Id;
//	}
//
//
//	public void setId(Long id) {
//		Id = id;
//	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
