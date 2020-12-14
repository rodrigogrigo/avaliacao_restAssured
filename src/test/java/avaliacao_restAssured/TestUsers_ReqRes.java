package avaliacao_restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hamcrest.Matchers;

public class TestUsers_ReqRes extends BaseUri_ReqRes {
	@Test
	public void testGetAllUsersRecords() {
		RestAssured
		.when()
			.get("/users?page=2")
		.then()
			.statusCode(STATUS_CODE_OK)
			.body("data[0].id", Matchers.is(7))
			.body("data[0].first_name", Matchers.is("Michael"))
			.body("data[0].last_name", Matchers.is("Lawson"))
			.body("data[0].avatar", Matchers.is("https://reqres.in/img/faces/7-image.jpg"));
	}
	
	@Test
	public void testGetSingleUserRecord() {
		RestAssured
		.when()
			.get("/users/2")
		.then()
			.statusCode(STATUS_CODE_OK)
			.body("data.id", Matchers.is(2))
			.body("data.first_name", Matchers.is("Janet"))
			.body("data.last_name", Matchers.is("Weaver"))
			.body("data.avatar", Matchers.is("https://reqres.in/img/faces/2-image.jpg"))
			.body("support.url", Matchers.is("https://reqres.in/#support-heading"))
			.body("support.text", Matchers.is("To keep ReqRes free, contributions towards server costs are appreciated!"));
	}

	@Test
	public void testGetSingleUserRecordNotFound() {
		RestAssured
		.when()
			.get("/users/23")
		.then()
			.statusCode(STATUS_CODE_NOT_FOUND);
	}
	
	@Test
	public void testPostUserRecord() {
		RestAssured
		.given()
			.body("{\"name\" : \"morpheus\", \"job\" : \"leader\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/users")
		.then()
			.statusCode(STATUS_CODE_CREATED)
			.body("name", Matchers.is("morpheus"))
			.body("job", Matchers.is("leader"));	
	}
	
	@Test
	public void testPutUserRecord() {
		RestAssured
		.given()
			.contentType(ContentType.JSON)
			.body("{\"name\" : \"morpheus\", \"job\" : \"zion resident\"}")
		.when()
			.put("/users/2")
		.then()
			.statusCode(STATUS_CODE_OK)
			.body("name", Matchers.is("morpheus"))
			.body("job", Matchers.is("zion resident"));
	}
	
	@Test
	public void testDeleteUserRecord() {
		RestAssured
		.when()
			.delete("/users/2")
		.then()
			.statusCode(STATUS_CODE_NO_CONTENT);
	}
	
	@Test
	public void testRegisterSucessfulApi() {
		RestAssured
		.given()
			.body("{\"email\" : \"eve.holt@reqres.in\", \"password\" : \"pistol\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/register")
		.then()
			.statusCode(STATUS_CODE_OK)
			.body("id", Matchers.is(4))
			.body("token", Matchers.is("QpwL5tke4Pnpja7X4"));
	}
	
	@Test
	public void testRegisterUnsucessfulApi() {
		RestAssured
		.given()
			.body("{\"email\" : \"sydney@fife\", \"password\" : \"pistol\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/register")
		.then()
			.statusCode(STATUS_CODE_BAD_REQUEST);
	}
	
	@Test
	public void testLoginSucessfulApi() {
		RestAssured
		.given()
			.body("{\"email\" : \"eve.holt@reqres.in\", \"password\" : \"cityslicka\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/login")
		.then()
			.statusCode(STATUS_CODE_OK)
			.body("token", Matchers.is("QpwL5tke4Pnpja7X4"));
	}
	
	@Test
	public void testLoginUnsucessfulApi() {
		RestAssured
		.given()
			.body("{\"email\" : \"peter@klaven\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/login")
		.then()
			.statusCode(STATUS_CODE_BAD_REQUEST);
	}
}
