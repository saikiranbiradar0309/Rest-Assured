package POJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommentsEndpointTest {

	public static void main(String[] args) {
		
		Comments comments = new Comments();
		
		comments.setPostId(102);
		comments.setName("Sai");
		comments.setEmail("sai@gmail.com");
		comments.setBody("This is comments Body");
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		request.body(comments);
		
		Response response = request.post("https://jsonplaceholder.typicode.com/comments");
		
		response.then().log().all();
		
		

	}

}
