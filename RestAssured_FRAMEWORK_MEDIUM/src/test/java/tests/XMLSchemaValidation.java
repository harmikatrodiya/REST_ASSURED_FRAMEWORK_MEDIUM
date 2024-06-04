package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;


/*Validate XML Schema
 * create XSD to classpath in the project - src/main/resources
 * add code to run api testing with REST assured
 * add code to validate XML response against XML Schema(XSD)
 * assertThat.body(matchesXsdInClasspath("calculator.xsd"));
 * */

public class XMLSchemaValidation {
	
	@Test
	public void schemaValidation() throws IOException {
		
File file = new File("./SoapRequest/Add.xml.txt");
		
		if (file.exists()) {
			System.out.println(" >> File Exists........");
		}
		FileInputStream fileInputStream = new FileInputStream(file);
		String requestBody = IOUtils.toString(fileInputStream,"UTF-8");
		
		baseURI = "https://ecs.syr.edu/";
		
	   	given()
	   	.contentType("text/xml")
		.accept(ContentType.XML)
		.body(requestBody)
		.when()
		.post("/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx")
		.then()
		.statusCode(200)
		.log().all()
		.and()
		.body("//*:AddResult.text()",equalTo("7"))
		.and()
		.assertThat().body(matchesXsdInClasspath("Calculator.xsd.txt"));
		
	}
}
