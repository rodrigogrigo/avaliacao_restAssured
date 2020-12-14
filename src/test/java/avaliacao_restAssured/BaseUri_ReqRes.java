package avaliacao_restAssured;
import org.junit.Before;

import static io.restassured.RestAssured.baseURI;

abstract class BaseUri_ReqRes {
	public static final int STATUS_CODE_OK = 200;
	public static final int STATUS_CODE_CREATED = 201;
	public static final int STATUS_CODE_NOT_FOUND = 404;
	public static final int STATUS_CODE_BAD_REQUEST = 400;
	public static final int STATUS_CODE_NO_CONTENT = 204;
	public static final int STATUS_CODE_INTERNAL_SERVER_ERROR = 500;
	
	@Before
	public void before() {
		baseURI = "https://reqres.in/api/";
	}
}
