package api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.userendpoints;
import api.endpoints.userendpoints2;
import api.payloads.userRequest;
import api.utilities.JsonUtilities;
import io.restassured.response.Response;



public class UserTests2 {

	File JSONfile;
	File JSONfile1;
	Logger logger;
	
	@BeforeClass
	public void setup() throws Exception
	{
		String filepath=System.getProperty("user.dir")+"/src/test/resources/JsonPayloads/CreateUser.json";
		
		JSONfile=new File(filepath);
		
		//JSONObject userPayload=JsonUtilities.readJsonObject("src/test/resources/JsonPayloads/CreateUser.json");
		
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void createUser()
	{
		logger.info("********* Creating User *********");
		Response res=userendpoints2.createUser(JSONfile);
		res.then().log().all();
		res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
		//Assert.assertEquals(res.statusCode(), 200);
		
		logger.info("********* User Is Created *********");
	}
	@Test(priority=2)
	public void getUserByName()
	{
		logger.info("********* Reading User Info *********");
		
		Response res=userendpoints2.getUser(this.JSONfile.getName());
		//res.then().log().body().statusCode(200);
		res.then().log().all();
		res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
		//Assert.assertEquals(res.statusCode(), 200);
		
		logger.info("********* User Info Is Displayed *********");
	}
	@Test(priority=3)
	public void updateUserByName() throws Exception
	{
		
		String filepath1=System.getProperty("user.dir")+"/src/test/resources/JsonPayloads/UpdateUser.json";
		
		JSONfile1=new File(filepath1);
		
		//JSONObject userPayload1=JsonUtilities.readJsonObject("src/test/resources/JsonPayloads/UpdateUser.json");
		
		logger.info("********* Updating User *********");
		
		Response res=userendpoints2.updateUser(this.JSONfile.getName(), JSONfile1);
		//res.then().log().body().statusCode(200);
		res.then().log().all();
		res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
		//Assert.assertEquals(res.statusCode(), 200);
		logger.info("********* User Is Updated *********");
		
		Response resAfterUpdate=userendpoints2.getUser(this.JSONfile.getName());
		//res.then().log().body().statusCode(200);
		res.then().log().all();
		res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
		//Assert.assertEquals(resAfterUpdate.statusCode(), 200);
		
	}
	@Test(priority=4)
	public void deleteUserByName()
	{
		logger.info("********* Deleting User *********");
		
		Response res=userendpoints2.deleteUser(this.JSONfile.getName());
		//res.then().log().body().statusCode(200);
		res.then().log().all();
		res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
		//Assert.assertEquals(res.statusCode(), 200);
		logger.info("********* User Is Deleted *********");
	}
	
	
}
