package Nested_POJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProductsEndpointTest {

	public static void main(String[] args) {
		
		Rating rating = new Rating();
		
		rating.setRate(4.2f);
		rating.setCount(220);
		
		
		Products products = new Products();
		
		products.setCategory("Electronics");
		products.setDescription("iPhone 18 Pro Max with 256GB Storage");
		products.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png");
		products.setPrice(168999f);
		products.setTitle("iPhone 18 Pro Max");
		products.setRating(rating);
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		request.body(products);
		
		Response response = request.post("https://fakestoreapi.com/products");
		
		response.then().log().all();

	}

}
