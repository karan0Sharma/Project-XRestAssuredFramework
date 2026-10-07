package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import files.payload;

public class StepDefinitions extends Utils {
	
	
	RequestSpecification request;
	
	Response response;
	
	static String place_id;
	
	
	@Given("I have addPlace API contract with {string} {string} {string} {string}")
	public void i_have_add_place_api_contract_with(String Name, String Address, String Language, String Website) throws IOException {
		request = given().spec(requestSpecification()).body(payload.addPlace(Name, Address, Language, Website));
	   
	}
	

	@When("user calls {string} with {string} http method")
	public void user_calls_with_http_method(String resource, String method) 
	{
	 
        APIResources resourceAPI = APIResources.valueOf(resource);
		
        if( method.equalsIgnoreCase("POST"))
        {
	    response = request.when().post(resourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
	    System.out.println(resourceAPI.getResource());
	    }
        
        else if( method.equalsIgnoreCase("GET"))
        {
        response = request.when().get(resourceAPI.getResource()).then().spec(responseSpecification()).extract().response();
        System.out.println(resourceAPI.getResource());
        }
	}

	
	@Then("expect status code to {int}")
	public void expect_status_code_to(int value) {
	    
	    assertEquals(response.getStatusCode(), value);
	    
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		String actual = getJsonPath(response, key);
		assertEquals(actual, value);
		
		
	}
	

	@Given("I have getPlace API contract with {string}")
	public void i_have_get_place_api_contract_with(String placeID) throws IOException  {
		
		request = given().spec(requestSpecification()).queryParam("place_id", placeID);
		
	}
	

	@Then("verify that the {string} with {string} http method has {string}")
	public void verify_that_the_with_http_method_has(String resource, String method, String expected) throws IOException {
	    
		place_id = getJsonPath(response, "place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_http_method(resource, method); 
		String actualname = getJsonPath(response, "name");
		assertEquals(actualname, expected);
	}


	@Given("I have delete Place API")
	public void i_have_delete_place_api() throws IOException {
	    
		request = given().spec(requestSpecification()).body(payload.deletePlace(place_id));
		
	}





}




