package dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;


@Data
public class Products {
	private String title;
	
	private String slug;
	
	private String description;
	
	private Integer price;
	
	private Category category;
	
	private List<String> images;
	
	private String creationAt;
	
	private String updatedAt;
}
