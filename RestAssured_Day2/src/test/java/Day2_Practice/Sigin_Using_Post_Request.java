package Day2_Practice;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Sigin_Using_Post_Request {

	public static void main(String[] args) {

		// 1. Store Request payload/body in JSON File

		/*
		 * visit http://localhost:8189/swagger-ui/index.html
		 * 
		 * Goto --> sign-in-sign-up-controller --> POST /auth/signin
		 * 
		 * Inside that you will get a request body and store that inside
		 * 
		 * src/test/resources As signInRequestPayload.json
		 * 
		 * The body looks like below { "email": "admin1@gmail.com", "password":
		 * "admin123" }
		 * 
		 */

		// 2. Capture RequestSpecification Object
		RequestSpecification request = RestAssured.given();

		// 3. Add content type header
		request.contentType("application/json");

		// 4. Read request payload from JSON File
		File signInRequestBody = new File(
				"/Users/saikiranbiradar/Rest-Assured/RestAssured_Day2/src/test/resources/signInRequestPayload.json");

		// 5. Add RequestPayload in RequestSpecification Object
		request.body(signInRequestBody);

		// 6. Select POST request
		Response response = request.post("http://localhost:8189/auth/signin");

		// 7. Capture and verify status code
		int statusCode = response.getStatusCode();
		MatcherAssert.assertThat(201, Matchers.equalTo(201));

		// 8. Capture and verify status line
		String statusLine = response.getStatusLine();
		MatcherAssert.assertThat(statusLine, Matchers.containsString("201"));
		System.out.println(statusLine);

		// 9. Capture and verify Response Time
		long responseTime = response.getTime();
		MatcherAssert.assertThat(responseTime, Matchers.lessThan(6000l));

		// 10. Capture and Verify response content type header
		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		// 11. capture and verify response rate-limit header
		String limit = response.header("rate-limit");
		// MatcherAssert.assertThat(limit, Matchers.equalTo(100));
		System.out.println(limit);

		// 12. capture and print Response Payload
		String responseBody = response.body().asPrettyString();
		System.out.println(responseBody);

	}

}
