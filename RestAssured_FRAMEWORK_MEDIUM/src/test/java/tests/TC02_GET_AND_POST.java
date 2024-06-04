package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class TC02_GET_AND_POST {

	// @Test
	public void GetTest() {

		baseURI = "https://reqres.in/api";

		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[1].first_name", equalTo("Lindsay")).
		body("data.first_name", hasItems("Lindsay", "Tobias")).
		log().all();

	}

	@Test
	public void PostTest() {

		// using map ----------
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "Harmi");
//		map.put("job", "QA Tester");
//		System.out.println("Map value : " + map);
//		JSONObject request = new JSONObject(map);

		// using json object ----------------
		JSONObject request = new JSONObject();
		request.put("name", "Renil");
		request.put("job", "Developer");
		System.out.println("JSONObject value : " + request.toJSONString());

		baseURI = "https://reqres.in/api";

		given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		post("/users").
		then().
		statusCode(201).
		log().all();

	}
}
