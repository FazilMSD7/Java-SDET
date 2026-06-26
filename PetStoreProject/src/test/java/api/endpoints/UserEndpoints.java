package api.endpoints;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	//created to get prop from routes.prop file
		static ResourceBundle getURL() {
			ResourceBundle routes = ResourceBundle.getBundle("routes");	//load routes prop file
			return routes;
		}
	
	
	public static Response createUser(User payload) {
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(payload)

				.when()
				.post(Routes.post_url);

		return response;
		}
	
	public static Response getUser(String userName) {
		Response response = given()
				.pathParam("username", userName)

				.when()
				.get(Routes.get_url);

		return response;
		}
	
	public static Response updateUser(User payload, String username) {
		String update_url = getURL().getString("update_url");	//using routes.prop file data
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(payload)
				.pathParam("username", username)

				.when()
				.put(update_url);

		return response;
		}
	
	public static Response deleteUser(String username) {
		String delete_url = getURL().getString("delete_url");
		
		Response response = given()
				.pathParam("username", username)

				.when()
				.delete(delete_url);

		return response;
		}
}
