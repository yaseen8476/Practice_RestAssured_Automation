package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.json.JSONObject;

import api.payloads.userRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userendpoints2 {

	
	public static Response createUser(File JSONfile)
	{
		
		
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(JSONfile)
				.log().all()
				
				.when()
				.post(routes.post_url);

				return res;
		
	}
	
	public static Response getUser(String Name)
	{
		
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("userName", Name)
		.log().all()
		
		.when()
		.get(routes.get_url);

		return res;
		
	}
	
	public static Response updateUser(String Name, File JSONfile)
	{
		
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(JSONfile)
				.pathParam("userName", Name)
				.log().all()
				
				.when()
				.put(routes.put_url);

				return res;
		

		
	}
	
	public static Response deleteUser(String Name)
	{
		
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userName", Name)
				.log().all()
				
				.when()
				.delete(routes.delete_url);

				return res;
		
		
	}
}
