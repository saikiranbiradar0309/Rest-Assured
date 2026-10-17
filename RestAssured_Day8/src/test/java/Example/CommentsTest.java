package Example;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommentsTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		File jsonFileRequestBody = new File("/Users/saikiranbiradar/Rest-Assured/RestAssured_Day8/src/test/resources/commentsRequestBody.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		// De-Serialization
		Comments comments = mapper.readValue(jsonFileRequestBody, Comments.class);
		
		String jsonRequestBody = mapper.writeValueAsString(comments);
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		request.body(jsonRequestBody);
		
		Response response = request.post("https://jsonplaceholder.typicode.com/comments");
		
		response.then().log().all();
		
		
		
	}

}
