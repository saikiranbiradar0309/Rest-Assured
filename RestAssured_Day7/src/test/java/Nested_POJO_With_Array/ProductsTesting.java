package Nested_POJO_With_Array;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProductsTesting {
	
	public static void main(String[] args) throws JsonProcessingException {
		
		Category category = new Category();
		
		category.setId(2);
		category.setImage("https://i.imgur.com/ZANVnHE.jpeg");
		category.setName("Electronics");
		category.setSlug("electronics");
		category.setCreationAt("2026-10-15T15:15:18.000Z");
		category.setUpdatedAt("2026-10-15T15:15:18.000Z");
		
		
		Products products = new Products();
		
		products.setTitle("Sleek Modern Laptop with Ambient Lighting");
		products.setSlug("Sleek Modern Laptop with Ambient Lighting");
		products.setPrice(49999);
		products.setDescription("Products Description");
		products.setCategory(category);
		products.setCreationAt("2026-10-15T15:15:18.000Z");
		products.setUpdatedAt("2026-10-15T15:15:18.000Z");
		
		List<String> imageList = List.of("https://i.imgur.com/OKn1KFI.jpeg"
				,"https://i.imgur.com/G4f21Ai.jpeg"
				,"https://i.imgur.com/Z9oKRVJ.jpeg");
		
		products.setImages(imageList);
		
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String productsJsonRequestBody = mapper.writeValueAsString(products);
		
		request.body(productsJsonRequestBody);
		
		Response response = request.post("https://api.escuelajs.co/api/v1/products");
		
		response.then().log().all();
		
		
	}
	
}
