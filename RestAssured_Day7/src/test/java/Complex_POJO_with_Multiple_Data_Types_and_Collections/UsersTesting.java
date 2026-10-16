package Complex_POJO_with_Multiple_Data_Types_and_Collections;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsersTesting {

	public static void main(String[] args) throws JsonProcessingException {
		
		Avatar avatar1 = new Avatar();
		
		avatar1.setName("Sai.png");
		avatar1.setPercent(100);
		avatar1.setSize(12345);
		avatar1.setStatus("done");
		avatar1.setType("image/jpeg");
		avatar1.setUid("rc-upload-17921097359789");
		avatar1.setUrl("https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/551.jpg");
		
		Avatar avatar2 = new Avatar();
		
		avatar2.setName("Saikiran.png");
		avatar2.setPercent(100);
		avatar2.setSize(12645);
		avatar2.setStatus("done");
		avatar2.setType("image/jpeg");
		avatar2.setUid("rcd-upload-17921097359789");
		avatar2.setUrl("https://cloudflare-ipfs.com/ipfs/Qmd3Wd5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/551.jpg");
		
		
		List<Avatar> listAvatar = List.of(avatar1, avatar2);
		
		Users users = new Users();
		users.setFirstName("Sai");
		users.setLastName("Biradar");
		users.setEmail("Sai@gmail.com");
		users.setStatus(false);
		users.setBirthday("2026-09-13T09:01:07.450Z");
		
		List<String> userSkills = List.of("AWS","Docker","Kubernetes");
		users.setSkills(userSkills);
		
		users.setAvatar(listAvatar);
		
		
		RequestSpecification request = RestAssured.given();
		
		request.log().all();
		
		request.contentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String usersJsonRequestBody = mapper.writeValueAsString(users);
		
		request.body(usersJsonRequestBody);
		
		Response response = request.post("https://api.fake-rest.refine.dev/users");
		
		response.then().log().all();
		
	}

}
