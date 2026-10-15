package OAuth2UserAPIs;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserApis {
	
	// Lets store Access token and refresh token globally
	String accessToken;
	String refreshToken;
	
	// Store employee id globally
	int employeeId;
	
	

	@BeforeClass
	public void setup()
	{
		// Generate access token and refresh token
		RequestSpecification request = RestAssured.given();
		
		request.contentType("application/x-www-form-urlencoded");
		
		request.formParam("grant_type", "password");			// Given by development team
		request.formParam("client_id", "employee_client");		// Given by development team
		request.formParam("client_secret", "employee_secret");	// Given by development team
		request.formParam("username", "Sai");					// Given by development team
		request.formParam("password", "Sai123");				// Given by development team
		request.formParam("scope", "read write");				// Given by development team
		
		
		request.log().all();
		
		Response response = request.post("http://localhost:9080/oauth2/token");
		
		response.then().log().all();
		
		
		// Now lets capture access token and refresh token
		JsonPath jsonPath = response.jsonPath();
		
		
		/*
		String accessToken = jsonPath.getString("accessToken");
		
		String refreshToken = jsonPath.getString("refreshToken");
		*/
		
		
		//To store globally follow this approach
		accessToken = jsonPath.getString("accessToken");
		refreshToken = jsonPath.getString("refreshToken");
	}
	
	
	@Test
	public void validateCreateEmployee()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.auth().oauth2(accessToken);
		
		request.contentType("application/json");
		
		// Read createEmployeePayload.json
		File requestBody = new File(System.getProperty("user.dir")+"//src//test//resources/payload/createEmployeePayload.json");
		
		// Attach the payload
		request.body(requestBody);
		
		Response response = request.post("http://localhost:9081/employees");
		
		response.then().log().all();
		
		
		// Lets get employee by id
		employeeId = response.jsonPath().getInt("id");
		
		
	}
	
	// I Ran the above test case once and tried running the test case after writting below test 
	// And I came through duplicate_email / Email already exists error
	// Because email is unique
	// So i modified the createEmployeePayload.json
	
	
	// NOTE
	// There is a defect in API's so The Department Name can be same for many employees but here
	// The Department Name also made unique by the developers
	// So make sure update the Department with Unique Name
	
	
	@Test(dependsOnMethods = {"validateCreateEmployee"})
	public void validateCreayedEmployeeUsingGetTestCases()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.auth().oauth2(accessToken);
		
		request.pathParam("id", employeeId);
		
		Response response = request.get("http://localhost:9081/employees/{id}");
		
		response.then().log().all();
	}
	
	
	@Test(dependsOnMethods = {"validateCreateEmployee"})
	public void validateUpdateEmployeeUsingPutTestCase()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.auth().oauth2(accessToken);
		
		request.contentType("application/json");
		
		request.pathParam("id", employeeId);
		
		// Read createEmployeePayload.json
		File requestPayload = new File(System.getProperty("user.dir")+"//src//test//resources//payload//updateEmployeePayload.json");
		
		// Attach the payload
		request.body(requestPayload);
		
		Response response = request.put("http://localhost:9081/employees/{id}");
		
		response.then().log().all();

	}
	
	@Test(priority = 4)
	public void validateDeleteEmployeeTestCase()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.auth().oauth2(accessToken);
		
		request.pathParam("id", employeeId);
		
		Response response = request.delete("http://localhost:9081/employees/{id}");
		
		response.then().log().all();
	}
	

}
