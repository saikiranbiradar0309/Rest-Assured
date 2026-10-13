package MyNotes;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuthentication {
	
	
	// Remove (enabled = false) When you're testing this
	// Added This only to check my below test case
	@Test(enabled = false)
	public void BasicAuth()
	{
		RestAssured.given()
						.auth().basic("Admin", "admin123")	
						.log().all()
					.when()	
						.get("http://localhost:8388/api/employees/3")
					.then()
						.log().all()
						.statusCode(200);
		
		
	}
	
	// Remove (enabled = false) When you're testing this
	// Added This only to check my below test case
	@Test(enabled = false)
	public void BasicAuthWithAssertion()
	{
		Response response = RestAssured
								.given()
									.auth().basic("Admin", "admin123")
								.when()
									.get("http://localhost:8388/api/employees/3");
		
		//response.statusCode() gets the actual HTTP status.
		//Matchers.equalTo(200) defines the expected status.
		//- MatcherAssert.assertThat() compares actual versus expected.
		
		MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(200));
	}
	
	
	@Test
	public void BasicAuthProfessional()
	{
		RestAssured.given()
						.auth().basic("Admin", "admin123")
						.log().all()
					.when()
						.get("http://localhost:8388/api/employees/3")
					.then()
						.log().all()
						.statusCode(200)
						.body("id", equalTo(3))
						.body("firstName", equalTo("Niranjan"))
						.body("lastName", equalTo("Shinde"))
						.body("country", equalTo("India"));
	}
	
}
