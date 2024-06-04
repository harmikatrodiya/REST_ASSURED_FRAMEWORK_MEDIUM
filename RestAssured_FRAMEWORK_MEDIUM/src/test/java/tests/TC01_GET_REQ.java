package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TC01_GET_REQ {

	@Test
	public void Test1() {
		 
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println("Status code is: "+response.getStatusCode());
		System.out.println("Response Time Is: "+response.getTime());
		System.out.println("Response Body is: "+response.getBody().asPrettyString());
		System.out.println("StatusLine is: " +response.getStatusLine());
		System.out.println( "Content Type is:"+response.getHeader("content-type"));
	
		int statusCode = response.getStatusCode(); 
		Assert.assertEquals(statusCode, 200);
	
	
	
	}
	
	@Test
	public void Test2() {
		
		baseURI ="https://reqres.in/api";
		
		given().
			get("users?page=2").
		then().
		statusCode(200).
		body("data[1].id",equalTo(8)).     //comparing id
		log().all();                     //print all log
		
	}
	
	
}
