package API_Basic_Auth;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {

	public static void main(String[] args) {
		
		RequestSpecification request = RestAssured.given();
		
		request.auth().basic("Admin", "admin123");
		
		Response response = request.get("http://localhost:8388/api/employees");
		
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		
		
		long responseTime = response.getTime();
		System.out.println(responseTime);
		
		
		Headers header = response.headers();
		System.out.println(header);
		
		
		String responseBody = response.getBody().asPrettyString();
		System.out.println(responseBody);

	}

}
