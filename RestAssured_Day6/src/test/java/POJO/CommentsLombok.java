package POJO;

import lombok.Data;



// Specifying Data because we're using Lombok
@Data
public class CommentsLombok {
	
	private int postId;
	
	private String name;
	
	private String email;
	
	private String body;

	
}
