package POJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostsEndpointTest {
	
	public static void main(String[] args) {
		
		Posts posts = new Posts();
		
		posts.setUserId(101);
		
		posts.setTitle("Ecomm Website");
		
		posts.setBody("We sell products");
		
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		request.body(posts);
		
		Response response = request.post("https://jsonplaceholder.typicode.com/posts");
		
		response.then().log().all();
		
	}
}
