package MultiLevel_Nested_POJO;

import lombok.Data;

@Data
public class Address {
	private Geolocation geolocation;
	
	private String city;
	
	private String street;
	
	private int number;
	
	private String zipcode;
}
