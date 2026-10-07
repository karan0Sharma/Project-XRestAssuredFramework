package resources;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(req == null)
		{
		
		PrintStream log = new PrintStream(new FileOutputStream("logResults.txt"));
		req = new RequestSpecBuilder().setBaseUri(globalValue("baseURL")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public ResponseSpecification responseSpecification()
	{
		ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resp;

	}
	
	public static String globalValue(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("/Users/karansharma/eclipse-workspace2/RestAssuredAPIFramework/src/test/java/resources/global.properties");
		
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	public String getJsonPath(Response response, String key)
	{
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
