package Example;

import lombok.Data;

@Data
public class Comments {
	
	private Integer postId;
	
	private String name;
	
	private String email;
	
	private String body;
}
