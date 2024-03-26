package vdantas.datum.api.reqres.in;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.CoreMatchers.containsString;

@SuppressWarnings("unused")
public class ApiReqresIn {

	// Parâmetro geral
	private String url = "https://reqres.in/api/users";

	// Parâmetro listuser
	private String idnumlu = "2";

	@Test
	public void getListUser() {

		given().param("page", "idnumlu").when().get(url).then().statusCode(200).body("page", equalTo(idnumlu));

	}

	// Parâmetro singleuser
	private String idnumsu = "2";

	@Test
	public void getSingleUser() {

		given().param("id", "idnumsu").when().get(url).then().statusCode(200).body("data.id", equalTo(idnumsu));

	}

	// Parâmetro create
	private String namecre = "Vini";
	private String jobcre = "Quality Assurance";

	@SuppressWarnings("unchecked")
	@Test
	public void postCreate() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "namecre");
		requestParams.put("job", "jobcre");

		given().body(requestParams.toJSONString()).when().post(url).then().statusCode(201)
				.body(containsString("createdAt"));
	}

	@Test
	public void patchUpdate() {
		String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

		given().contentType(ContentType.JSON).body(requestBody).when().patch("url" + "/2").then().assertThat()
				.statusCode(200).body("name", equalTo("morpheus")).body("job", equalTo("zion resident"));
	}

}
