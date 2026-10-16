package MultiLevel_Nested_POJO;

import lombok.Data;

@Data
public class Users {
	
	private String name;
	
	private String username;
	
	private String email;
	
	private Address address;
	
	private String phone;
	
	private String website;
	
	private Company company;
	
}
