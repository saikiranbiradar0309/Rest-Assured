package API_Day1;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {

	public static void main(String[] args) {
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.delete("http://localhost:9191/api/employees/55");
		
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		
		long responseTime = response.getTime();
		System.out.println(responseTime);
		
		Headers header = response.headers();
		System.out.println(header);

	}

}
