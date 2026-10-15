package POJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommentsLombokEndpointTest {

	public static void main(String[] args) {
		
		CommentsLombok comments = new CommentsLombok();
		
		comments.setPostId(103);
		comments.setName("Saikiran");
		comments.setEmail("saik@gmail.com");
		comments.setBody("This is comments Body for Sai");
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		request.body(comments);
		
		Response response = request.post("https://jsonplaceholder.typicode.com/comments");
		
		response.then().log().all();

	}

}
