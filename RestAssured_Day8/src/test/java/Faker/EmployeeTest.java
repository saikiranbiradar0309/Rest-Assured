package Faker;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EmployeeTest {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		File employeeRequestPayload = new File("/Users/saikiranbiradar/Rest-Assured/RestAssured_Day8/src/test/resources/employeeRequestBody.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Employee employee = mapper.readValue(employeeRequestPayload, Employee.class);
		
		Faker faker = new Faker();
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String email = faker.internet().emailAddress();
		//String number = faker.number().digits(10);
		String city = faker.address().city();
		String country = faker.address().country();
		
		employee.setFirstName(fname);
		employee.setLastName(lname);
		employee.setEmail(email);
		//employee.setMobileNumber(mobile);
		employee.setCity(city);
		employee.setCountry(country);
		
		String jsonRequestBody = mapper.writeValueAsString(employee);
		
		RequestSpecification request = RestAssured.given();
		request.log().all();
		
		request.contentType("application/json");
		
		request.body(jsonRequestBody);
		
		Response response = request.post("http://localhost:9191/api/employees");
		
		response.then().log().all();
		
		
	}

}
