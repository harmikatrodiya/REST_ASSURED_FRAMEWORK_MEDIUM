package tests;

import static io.restassured.RestAssured.*;
//import static io.restassured.RestAssured.baseURI;
//import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import io.restassured.module.jsv.JsonSchemaValidator;
//import static org.hamcrest.Matchers.*;

/* VALIDATE JSON SCHEMA
 * 1. create json schema, steps below,
 * - open reqres get users api
 * - copy response
 * - open json schema conveter on web browser
 * - paste json response here
 * - click on generate json schema
 * - copy that response 
 * - paste at schema.json file in target folder
*2. add json schema file in classpath, steps below
* - right click on target folder in the eclipse
* - click on properties
* - click on location icon
* - click on taget
* - right click and create file like schema.json and save 
* - close
* 3. add maven dependency for json schema validator which belongs from io.restassured 
* 4. create a new function to validate json response against schema
* 
*/



//4. create a new function to validate json response against schema
public class JSON_Schema_Validator {

	@Test
	public void GetTest() {

		baseURI = "https://reqres.in/api";

		given().get("/users?page=2")
		.then()
		.assertThat()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"))
		.log().all();
		
		
		/*.body(matchesJsonSchemaInClasspath("schema.json"))
		.statusCode(200)
		.log().all();*/

	}
}
