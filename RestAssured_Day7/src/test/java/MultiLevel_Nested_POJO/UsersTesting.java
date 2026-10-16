package MultiLevel_Nested_POJO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsersTesting {

	public static void main(String[] args) throws JsonProcessingException {
		
		Geo geo = new Geo();
		
		geo.setLat("12° 55′ 30″ N");
		geo.setLng("77° 36′ 31″ E");
		
		
		Address address = new Address();
		
		address.setCity("Bengaluru");
		address.setGeo(geo);
		address.setStreet("BTM Layout");
		address.setSuite("Nesto paradise");
		address.setZipcode("560068");
		
		
		Company company = new Company();
		
		company.setName("50Gramx");
		company.setCatchPhrase("Cloud Service Provider");
		company.setBs("cloud, aws, gcp, azure");
		
		
		Users users = new Users();
		
		users.setAddress(address);
		users.setCompany(company);
		users.setEmail("sai@gmail.com");
		users.setName("Saikiran");
		users.setPhone("6534143576");
		users.setUsername("Sai");
		users.setWebsite("saikbiradar.in");
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		//request.body(users); // Automatically Rest Assured will convert Java Object into JSON object in Runtime
		
		// We convert Java Object into JSON Format using Serialization Concept (Jackson Databind Dependency)
		// We convert Java Object into XML Format using Serialization Concept (Jackson XML Dependency)
		
		//ObjectMapper mapper1 = new ObjectMapper();
		//XmlMapper mapper2 = new XmlMapper();
		
		
		//String RequestBody1 = mapper1.writeValueAsString(mapper1);
		//String RequestBody2 = mapper2.writeValueAsString(mapper1);
		
		//request.body(RequestBody1);
		//request.body(RequestBody2);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonUserRequestBody = mapper.writeValueAsString(users);
		
		request.body(jsonUserRequestBody);
		
		
		Response response = request.post("https://jsonplaceholder.typicode.com/users");
		
		response.then().log().all();

	}

}
