package Nested_POJO_With_Array;

import java.util.List;

import lombok.Data;

@Data
public class Products {
	
	private String title;
	
	private String slug;
	
	private Integer price;
	
	private String description;
	
	private Category category;
	
	private List<String> images;
	
	private String creationAt;
	
	private String updatedAt;
	
}
