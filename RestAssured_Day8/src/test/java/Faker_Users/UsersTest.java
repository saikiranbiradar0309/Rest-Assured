package Faker_Users;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;

public class UsersTest {
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		File usersRequestPayloadPath = new File("/Users/saikiranbiradar/Rest-Assured/RestAssured_Day8/src/test/resources/usersRequestPayload.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Users users =  mapper.readValue(usersRequestPayloadPath, Users.class);
		
		Faker faker = new Faker();
		
		users.setEmail(faker.internet().emailAddress());
		users.setUsername(faker.internet().emailAddress());
		users.setPassword(faker.internet().password());
		
		
		
		Name name = new Name();
		name.setFirstname(faker.name().firstName());
		name.setLastname(faker.name().lastName());
		users.setName(name);
		
		Address address = new Address();
		address.setCity(faker.address().city());
		address.setStreet(faker.address().streetAddress());
		address.setZipcode(faker.address().zipCode());
		address.setNumber(Integer.parseInt(faker.number().digits(4)));
		users.setAddress(address);
		
		Geolocation geolocation = new Geolocation();
		geolocation.setLat(faker.address().latitude());
		geolocation.setGeolong(faker.address().longitude());
		address.setGeolocation(geolocation);
		
		String jsonRequestBody = mapper.writeValueAsString(users);
		
		
		RestAssured.given().contentType("application/json").body(jsonRequestBody).log().all().post("https://fakestoreapi.com/users").then().log().all();
		
	}
}
