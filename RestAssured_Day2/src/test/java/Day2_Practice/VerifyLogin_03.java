package Day2_Practice;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class VerifyLogin_03 {

	public static void main(String[] args) {
		
		RequestSpecification request = RestAssured.given();
		
		request.contentType("application/json");
		
		File signinRequestBody = new File("/Users/saikiranbiradar/Rest-Assured/RestAssured_Day2/src/test/resources/signInRequestPayload.json");
		
		request.body(signinRequestBody);
		
		Response response = request.post("http://localhost:8189/auth/signin");
		
		
		int statusCode = response.getStatusCode();
		MatcherAssert.assertThat(statusCode, Matchers.equalTo(201));
		
		
		String statusLine = response.getStatusLine();
		MatcherAssert.assertThat(statusLine, Matchers.containsString("201"));
		
		
		long responseTime = response.getTime();
		MatcherAssert.assertThat(responseTime, Matchers.lessThan(5000l));
		
		
		//String type = response.header("Content-Type");
		//MatcherAssert.assertThat(type, Matchers.containsString("application/json"));
		
		
		//String limit = response.header("rate-limit");
		//MatcherAssert.assertThat(type, Matchers.equalTo(100));
		
		String responseBody = response.body().asPrettyString();
		System.out.println(responseBody);
	}

}
