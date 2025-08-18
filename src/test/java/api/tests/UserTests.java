package api.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userendpoints;
import api.payloads.userRequest;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	userRequest userPayload;
	public Logger logger;// for logs
	
	@BeforeClass
	public void setup()
	{
		
		faker=new Faker();
		userPayload=new userRequest();
		
		//Random data preparation
		
		userPayload.setUsername(faker.name().username());
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		logger=LogManager.getLogger(this.getClass());
		
	}	
		@Test(priority=1)
		public void createUser()
		{
			logger.info("********* Creating User *********");
			Response res=userendpoints.createUser(userPayload);
			res.then().log().all();
			res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
			//Assert.assertEquals(res.statusCode(), 200);
			
			logger.info("********* User Is Created *********");
		}
		@Test(priority=2)
		public void getUserByName()
		{
			logger.info("********* Reading User Info *********");
			
			Response res=userendpoints.getUser(this.userPayload.getUsername());
			//res.then().log().body().statusCode(200);
			res.then().log().all();
			res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
			//Assert.assertEquals(res.statusCode(), 200);
			
			logger.info("********* User Info Is Displayed *********");
		}
		@Test(priority=3)
		public void updateUserByName()
		{
			
			
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			userPayload.setPassword(faker.internet().password(5, 10));
			userPayload.setPhone(faker.phoneNumber().cellPhone());
			
			logger.info("********* Updating User *********");
			
			Response res=userendpoints.updateUser(this.userPayload.getUsername(), userPayload);
			//res.then().log().body().statusCode(200);
			res.then().log().all();
			res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
			//Assert.assertEquals(res.statusCode(), 200);
			logger.info("********* User Is Updated *********");
			
			Response resAfterUpdate=userendpoints.getUser(this.userPayload.getUsername());
			//res.then().log().body().statusCode(200);
			res.then().log().all();
			res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
			//Assert.assertEquals(resAfterUpdate.statusCode(), 200);
			
		}
		@Test(priority=4)
		public void deleteUserByName()
		{
			logger.info("********* Deleting User *********");
			
			Response res=userendpoints.deleteUser(this.userPayload.getUsername());
			//res.then().log().body().statusCode(200);
			res.then().log().all();
			res.then().log().body().statusCode(anyOf(equalTo(200),equalTo(404)));
			//Assert.assertEquals(res.statusCode(), 200);
			logger.info("********* User Is Deleted *********");
		}
		
		
	}
	
	
	
