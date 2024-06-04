package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TC04_TestOnLocalAPI {

	@Test
	public void get() {

		baseURI = "http://localhost:3000";

		given().get("/users").then().statusCode(200).log().all();

	}

	@Test
	public void Post() {

		JSONObject request = new JSONObject();
		request.put("firstname", "Shiv");
		request.put("lasttname", "Tejani");
		request.put("subjectId", 1);

		baseURI = "http://localhost:3000";

		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.post("/users")
		.then()
		.statusCode(201)
		.log().all();

	}
	
	@Test
	public void Put() {

		JSONObject request = new JSONObject();
		request.put("firstname", "Shivag");
		request.put("lasttname", "Tejani");
		request.put("subjectId", 1);

		baseURI = "http://localhost:3000";

		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.put("/users/4")
		.then()
		.statusCode(200)
		.log().all();

	}
	
	@Test
	public void Patch() {

		JSONObject request = new JSONObject();
	
		request.put("lasttname", "Patel");
		
		baseURI = "http://localhost:3000";

		given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(request.toJSONString())
		.when()
		.patch("/users/6")
		.then()
		.statusCode(200)
		.log().all();

	}
	
	@Test
	public void Delete() {

		
		baseURI = "http://localhost:3000";
		
		when().delete("/users/7").then().statusCode(200);
		
	}

}
