package Faker_Users;

import lombok.Data;

@Data
public class Address {
	
	private Geolocation geolocation;
	
	private String city;
	
	private String street;
	
	private Integer number;
	
	private String zipcode;
}
