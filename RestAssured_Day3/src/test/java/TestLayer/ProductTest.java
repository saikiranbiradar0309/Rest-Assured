package TestLayer;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductTest {
	
	@Test
	public void verifyProductResponse()
	{
		Response response = RestAssured.given().get("https://api.escuelajs.co/api/v1/products/28");
		
		//1. Get Status Code
		MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
		
		
		//2. Get Status Line
		MatcherAssert.assertThat(response.getStatusLine(), Matchers.containsString("200 OK"));
		
		
		//3. Response Time
		MatcherAssert.assertThat(response.getTime(), Matchers.lessThan(5000L));
		
		
		//4. Content-Type Header
		MatcherAssert.assertThat(response.header("Content-Type"), Matchers.containsString("application/json"));
		
		
		//5. Display response for debugging
		response.prettyPrint();
	}
}
