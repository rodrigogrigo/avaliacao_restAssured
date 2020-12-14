package avaliacao_restAssured;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.RestAssured;

public class TestResource_ReqRes extends BaseUri_ReqRes {
	/**/
	@Test
	public void testGetAllResourceRecords() {
		RestAssured
		.when()
			.get("/unknown")
		.then()
			.statusCode(STATUS_CODE_OK)
			.body("data[0].id", Matchers.is(1))
			.body("data[0].name", Matchers.is("cerulean"))
			.body("data[0].year", Matchers.is(2000))
			.body("data[0].color", Matchers.is("#98B2D1"))
			.body("data[0].pantone_value", Matchers.is("15-4020"));
	}
	
	@Test
	public void testGetSingleResourceRecord() {
		RestAssured
		.when()
			.get("/unknown/2")
		.then()
			.statusCode(STATUS_CODE_OK)
			.body("data.id", Matchers.is(2))
			.body("data.name", Matchers.is("fuchsia rose"))
			.body("data.year", Matchers.is(2001))
			.body("data.color", Matchers.is("#C74375"))
			.body("data.pantone_value", Matchers.is("17-2031"))
			.body("support.url", Matchers.is("https://reqres.in/#support-heading"))
			.body("support.text", Matchers.is("To keep ReqRes free, contributions towards server costs are appreciated!"));				
	}
	
	@Test
	public void testGetSingleResourceRecordNotFound() {
		RestAssured
		.when()
			.get("/unknown/23")
		.then()
			.statusCode(STATUS_CODE_NOT_FOUND);
	}
	

}
