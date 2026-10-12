package Day2_Practice;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicResponseObject_01 {

	public static void main(String[] args) {
		
		RequestSpecification request = RestAssured.given();
		
		request.auth().basic("Admin", "admin123");
		
		Response response = request.get("http://localhost:8388/api/employees/6");
		
		int statusCode = response.getStatusCode();
		
		String statusLine = response.getStatusLine();
		
		long responseTime = response.getTime();
		
		Headers header = response.headers();
		
		String responseBody = response.getBody().asPrettyString();
		
		
		
		System.out.println(statusCode);
		System.out.println(statusLine);
		System.out.println(responseTime);
		System.out.println(header);
		System.out.println(responseBody);

	}

}
