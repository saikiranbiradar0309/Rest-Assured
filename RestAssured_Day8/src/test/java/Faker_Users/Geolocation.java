package Faker_Users;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Geolocation {
	
	private String lat;
	
	@JsonProperty("long")
	private String geolong;
}
