package MadeEasyTutorial;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MadeEasyApi {
	
	// Defining token globally
	String token;
	
	
	// Defining Category ID Globally
	int categoryId;
	
	// Defining Product ID Globally
	int productId;
	
	@Test
	public void validateAdminLoginApi()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		//String loginPath = System.getProperty("user.dir")+"//src/test/resources/payload/AdminLoginPayload.json";
		//File loginPayload = new File("loginPath");
		
		File loginPayload = new File("/Users/saikiranbiradar/Rest-Assured/RestAssured_Day4/src/test/resources/payload/AdminLoginPayload.json");
		
		request.body(loginPayload);
		
		
		Response response = request.post("http://localhost:8189/auth/signin");
		
		
		String responsePayload = response.body().asPrettyString();
		//System.out.println(responsePayload);
		
		
		JsonPath jsonPath = response.jsonPath();
		
		// First lets get the token
		//String token = jsonPath.getString("token");
		
		// But the problem is we need token to AddNewCategory and into some other test
		// But we defined token inside a class so we cannot able to call that in another class
		// For that we need to define token globally
		// SO here we're doing like below
		
		token = jsonPath.getString("token");
		
		
		response.then().log().all();
		
	}
	
	
	// Here we should compulsory add dependsOnMethods 
	// Else we will get error 
	@Test(dependsOnMethods = {"validateAdminLoginApi"})
	public void validateAddNewCategoryApi()
	{
		
		// We need to specify token here else we will get 401 Unauthorized error
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		
		// In the last(Means in this test case) add this step To know about errors we're
		// Getting before
		
		
		// So here we're adding access token
		request.auth().oauth2(token);
		
		
		File categoryPayload = new File("/Users/saikiranbiradar/Rest-Assured/RestAssured_Day4/src/test/resources/payload/NewCategoryPayload.json");
		
		request.body(categoryPayload);
		
		Response response = request.post("http://localhost:8189/category");
		
		String responsePayload = response.body().asPrettyString();
		//System.out.println(responsePayload);
		
		// Before We i ran this application I wasn't configured Assertions
		// SO my test cases showed passed but i got 401 Unauthorized error
		// for validateAddNewCategoryApi Test
		
		// When i added Assertion In the console Its showing One test case 
		// as failure
		MatcherAssert.assertThat(response.statusCode(), Matchers.equalTo(201));
		
		
		// We need to capture Category ID from Response Payload
		JsonPath jsonPath = response.jsonPath();
		
		categoryId = jsonPath.getInt("id");
		
		response.then().log().all();
	
	}
	
	@Test(dependsOnMethods = {"validateAddNewCategoryApi"})
	public void validateGetNewCategoryApi()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.auth().oauth2(token);
		
		// The first "categoryId" will be present in swagger documentation
		// Second categoryId will be the which we defined
		request.pathParam("categoryId", categoryId);
		
		
		Response response = request.get("http://localhost:8189/category/{categoryId}");
	
		String responseBody = response.body().asPrettyString();
		//System.out.println(responseBody);
		
		
		response.then().log().all();
	}
	
	
	// Enabled= false to skip this testcase
	@Test(dependsOnMethods = {"validateGetNewCategoryApi"}, enabled=false)
	public void validateDeleteCategoryApi()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.auth().oauth2(token);
		
		request.pathParam("categoryId", categoryId);
		
		Response response = request.delete("http://localhost:8189/category/{categoryId}");
	
		//System.out.println("Category ID Deleted Successfully");
		
		response.then().log().all();
	}
	
	
	@Test(dependsOnMethods = {"validateAddNewCategoryApi"})
	public void validateAddNewProductApi()
	{
		File productPayload = new File("/Users/saikiranbiradar/Rest-Assured/RestAssured_Day4/src/test/resources/payload/NewProductPayload.json");
		
		// Lets have method chaining here   ***********
		
		Response response = RestAssured
								.given()
									.auth().oauth2(token)
										.contentType("application/json")
											.body(productPayload)
												.log().all()
													.post("http://localhost:8189/products");
		
		response.body().asPrettyString();
		
		response.then().log().all();
		
		
		productId = response.jsonPath().getInt("id");
	}
	
	@Test(dependsOnMethods = {"validateAddNewProductApi"})
	public void validateGetProductApi()
	{
		RestAssured
			.given()
				.auth().oauth2(token)
					.pathParam("productId", productId)
						.log().all()
							.get("http://localhost:8189/products/{productId}")
								.then().log().all();
	}
	
	
	@Test(dependsOnMethods = "validateGetProductApi")
	public void validateUploadProductImage()
	{
		RequestSpecification request = RestAssured.given();
		
		request.auth().oauth2(token);
		
		String imagePath = "/Users/saikiranbiradar/Rest-Assured/RestAssured_Day4/src/test/resources/payload/SamsungS25.jpeg";
	
		File imagePayload = new File(imagePath);
		
		request.multiPart("imageFile", imagePayload);
		
		request.pathParam("productId", productId);
		
		request.log().all();
		
		Response response = request.post("http://localhost:8189/products/{productId}/image");
		
		response.then().log().all();
		
	}
}
