package steps;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.Category;
import dto.Products;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProductsStepsTest {
	
	public static void main(String[] args) throws JsonProcessingException {
		
		Products products = new Products();
		
		products.setTitle("iPhone 18");
		products.setSlug("iPhone 18");
		products.setDescription("iPhone 18 on sale");
		products.setPrice(1000);
		
		// Initialize the nested Category object first
		products.setCategory(new Category());
		
		
		
		products.getCategory().setId(3);
		products.getCategory().setName("Electronics");
		products.getCategory().setSlug("Electronics");
		products.getCategory().setImage("https://www.apple.com/a1.png");
		products.getCategory().setUpdatedAt(LocalDateTime.of(2026, 10, 17, 6, 37, 58) 
				.atOffset(ZoneOffset.UTC) 
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
		products.getCategory().setUpdatedAt(LocalDateTime.of(2026, 10, 17, 6, 37, 58) 
				.atOffset(ZoneOffset.UTC) 
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
		
		products.setImages(List.of("https://www.apple.com/a1.png",
				"https://www.apple.com/a2.png",
				"https://www.apple.com/a3.png"));
		
		products.setCreationAt(LocalDateTime.of(2026, 10, 17, 6, 37, 58) 
				.atOffset(ZoneOffset.UTC) 
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
		products.setUpdatedAt(LocalDateTime.of(2026, 10, 17, 6, 37, 58) 
				.atOffset(ZoneOffset.UTC) 
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
		
		
		ObjectMapper mapper = new ObjectMapper();

		String jsonRequestPayload = mapper.writeValueAsString(products);
		
		
		RequestSpecification request = RestAssured.given();
		request.log().all();
		
		request.contentType("application/json");
		request.body(jsonRequestPayload);
		
		Response response = request.post("https://api.escuelajs.co/api/v1/products");
		
		response.then().log().all();
		
	}
}
