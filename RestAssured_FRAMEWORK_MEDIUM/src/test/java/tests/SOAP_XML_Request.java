package tests;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;

/* get request body from file
 * create a file with xml extension
 * copy request body from calculator soap api and save file 
 * <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
    <Body>
        <Add xmlns="http://tempuri.org/">
            <a>5</a>
            <b>2</b>
        </Add>
    </Body>
</Envelope>

get file in code
add code to check file exists

*/

/* Validate XML Response body
 * open freeformatrer.com
 * paste xml response from log
 * add XPath expression : //*:AddResult/text()
 * Evaluate XPath
 * add this after body .and()
		.body("//*:AddResult.text()",equalTo("7"));  //*:AddResult.text()  
 * */

public class SOAP_XML_Request {

	@Test
	public void validateSoapXML() throws IOException {
		
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
		.body("//*:AddResult.text()",equalTo("7"));
	
		
	}
	
	
	
}
