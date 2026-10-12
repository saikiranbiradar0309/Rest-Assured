
package Day2_Practice;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JSON_Schema_Validator {

    public static void main(String[] args) {

        // 1. Capture RequestSpecification
        RequestSpecification request = RestAssured.given();

        // 2. Set content type
        request.contentType("application/json");

        // 3. Read request payload
        File signInRequestBody =
                new File("src/test/resources/signInRequestPayload.json");

        request.body(signInRequestBody);

        // 4. Send POST request
        Response response =
                request.post("http://localhost:8189/auth/signin");

        // 5. Validate status code
        int statusCode = response.getStatusCode();

        MatcherAssert.assertThat(
                statusCode,
                Matchers.equalTo(201)
        );

        // 6. Validate status line
        String statusLine = response.getStatusLine();

        MatcherAssert.assertThat(
                statusLine,
                Matchers.containsString("201")
        );

        System.out.println("Status Line: " + statusLine);

        // 7. Validate response time
        long responseTime = response.getTime();

        MatcherAssert.assertThat(
                "Response time exceeded 6000 ms",
                responseTime,
                Matchers.lessThan(6000L)
        );

        System.out.println("Response Time: " + responseTime + " ms");

        // 8. Validate Content-Type
        String contentType = response.getContentType();

        MatcherAssert.assertThat(
                contentType,
                Matchers.containsString("application/json")
        );

        System.out.println("Content Type: " + contentType);

        // 9. Capture rate-limit header
        String limit = response.getHeader("rate-limit");

        System.out.println("Rate Limit: " + limit);

        // 10. Capture response body
        String responseBody = response.asPrettyString();

        System.out.println("Response Body:\n" + responseBody);

        // 11. Read JSON Schema
        File schemaFile =
                new File("src/test/resources/signInJsonSchema.json");

        // 12. Validate response against JSON Schema
        MatcherAssert.assertThat(
                responseBody,
                JsonSchemaValidator.matchesJsonSchema(schemaFile)
        );

        System.out.println("JSON Schema validation passed!");
        
        
        // How to Capture any values from Response Payload
        
        //1. Capture JsonPath Object so we can capture value from response payload
        JsonPath jsonPath = response.jsonPath();
        
        
        //2. Capture id
        int id = jsonPath.getInt("id");
        System.out.println("Id is: "+ id);
        
        
        //3. Capture role
        String role = jsonPath.getString("role");
        System.out.println("Role is: " + role);
        
        
        //4. capture email
        String mail = jsonPath.getString("email");
        System.out.println("Email is: " + mail);
    }
}
