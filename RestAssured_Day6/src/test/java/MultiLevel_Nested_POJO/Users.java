package MultiLevel_Nested_POJO;

import lombok.Data;

@Data
public class Users {
	
	private Address address;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private Name name;
	
	private String phone;
	
	private int __v;
}
