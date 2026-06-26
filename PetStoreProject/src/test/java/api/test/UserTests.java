package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker = new Faker();
	User userPayload;
	
	private static final Logger logger =
	        LogManager.getLogger(UserTests.class);		// for logs
	
	@BeforeClass
	public void setupdata() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
	}
	
	@Test(priority = 1)
	public void postUser() {
		logger.info(" ** Creating user **");
		
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println(
			    UserTests.class.getClassLoader().getResource("log4j2.xml")
			);
		
		logger.info(" ** user created **");
	}
	
	@Test(priority = 2)
	public void getUser() {
		logger.info(" ** Reading user info **");
		
		Response response = UserEndpoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" ** user info is displayed **");
	}
	
	@Test(priority = 3)
	public void updateUser() {
		logger.info(" ** Updating user **");
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndpoints.updateUser(userPayload, this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//check status after update
		Response responseAfterupdate = UserEndpoints.getUser(this.userPayload.getUsername());
		responseAfterupdate.then().log().all();
		
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		logger.info(" ** user info updated **");
	}
	
	@Test(priority = 4)
	public void deleteUser() {
		logger.info(" ** Deleting user **");
		
		Response response = UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" ** user deleted **");
		logger.error("This is an error message");
	}
}
