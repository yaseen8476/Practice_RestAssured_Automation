package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payloads.userRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userendpoints {

	
	public static Response createUser(userRequest payload)
	{
		
		
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				
				.when()
				.post(routes.post_url);

				return res;
		
	}
	
	public static Response getUser(String userName)
	{
		
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("userName", userName)
		
		.when()
		.get(routes.get_url);

		return res;
		
	}
	
	public static Response updateUser(String userName, userRequest payload)
	{
		
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("userName", userName)
				
				.when()
				.put(routes.put_url);

				return res;
		

		
	}
	
	public static Response deleteUser(String userName)
	{
		
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userName", userName)
				
				.when()
				.delete(routes.delete_url);

				return res;
		
		
	}
}
