package Day2_Practice;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyUsingAssertions_02 {

	public static void main(String[] args) {
		
		RequestSpecification request = RestAssured.given();
		
		request.auth().basic("Admin", "admin123");
		
		Response response = request.get("http://localhost:8388/api/employees/6");
		
		int statusCode = response.getStatusCode();
		MatcherAssert.assertThat(statusCode, Matchers.equalTo(201));
		
		MatcherAssert.assertThat(response.getStatusLine(), Matchers.containsString("200"));

	}

}
