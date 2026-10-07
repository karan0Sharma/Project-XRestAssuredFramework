package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	StepDefinitions m = new StepDefinitions();
	
	@Before("@DeletePlace")
	public void execute() throws IOException
	{
		
		if(StepDefinitions.place_id == null)
		{
		m.i_have_add_place_api_contract_with("Black House", "New Delhi, India", "en_IN", "www.google.co.in");
		m.user_calls_with_http_method("addPlace", "POST");
		m.verify_that_the_with_http_method_has("getPlace", "GET", "Black House");
		}
	}

}




