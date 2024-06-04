package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class TC03_PUT_PATCH_DELETE {

	@Test
	public void PutTest() {

		JSONObject request = new JSONObject();
		request.put("name", "Harmisha");
		request.put("job", "Tester");
		System.out.println("JSON Object Values: " + request);

		baseURI="https://reqres.in/api";
		
		given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		put("/users/2").
		then().
		statusCode(200).
		log().all();
	}
	
	@Test
	public void PatchTest() {

		JSONObject request = new JSONObject();
		request.put("name", "HK");
		request.put("job", "Tester");
		System.out.println("JSON Object Values: " + request);

		baseURI="https://reqres.in/api";
		
		given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		patch("/users/2").
		then().
		statusCode(200).
		log().all();
	}
	
	@Test
	public void DeleteTest() {

		baseURI="https://reqres.in";
		
		when().
		delete("/api/users/2").
		then().
		statusCode(204).
		log().all();
	}
}
