package MultiLevel_Nested_POJO;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsersTesting {

	public static void main(String[] args) {
		
		Geolocation geolocation = new Geolocation();
		
		geolocation.setGeoLong("17.9200");
		geolocation.setLat("77.5197");
		
		
		Address address = new Address();
		
		address.setCity("Bidar");
		address.setNumber(1235);
		address.setGeolocation(geolocation);
		address.setStreet("Mohan Market Bidar");
		address.setZipcode("585401");
		
		
		Name name = new Name();
		
		name.setFirstname("Saikiran");
		name.setLastname("Biradar");
		
		
		Users users = new Users();
		
		users.set__v(0);
		users.setAddress(address);
		users.setEmail("sai@gmail.com");
		users.setName(name);
		users.setPassword("Abc@123");
		users.setPhone("9087124157");
		users.setUsername("sai");
		
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		request.body(users);
		
		Response response = request.post("https://fakestoreapi.com/users");
		
		response.then().log().all();

	}

}
