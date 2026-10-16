package Complex_POJO_with_Multiple_Data_Types_and_Collections;

import java.util.List;

import lombok.Data;

@Data
public class Users {
	
	
	private String firstName;
	private String lastName;
	private String email;
	private Boolean status;
	private String birthday;
	private List<String> skills;
	private List<Avatar> avatar;
	
}
