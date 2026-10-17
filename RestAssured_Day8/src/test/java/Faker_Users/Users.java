package Faker_Users;

import java.util.List;

import com.github.javafaker.Avatar;

import lombok.Data;

@Data
public class Users {
	
	private Address address;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private Name name;
	
	private String phone;
	
	private Integer __v;
	
}
