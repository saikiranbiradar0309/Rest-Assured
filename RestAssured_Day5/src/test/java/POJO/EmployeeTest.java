package POJO;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeeTest {
	
	@Test
	public void createEmployee()
	{
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.auth().basic("Admin", "admin123");
		
		Employee emp = new Employee();
		
		emp.setFirstName("Amar");
		emp.setLastName("Parshetty");
		emp.setEmail("amar@gmail.com");
		emp.setMobileNumber("8765431097");
		emp.setCountry("INDIA");
		
		
		// DOnt fail to add this
		request.contentType(ContentType.JSON);
		
		// Rest Assured will convert Java Object into JSON object	--> Serialization Concept
		request.body(emp);
		
		Response response = request.post("http://localhost:8388/api/employees");
		
		response.then().log().all();
		
	}
	
}
