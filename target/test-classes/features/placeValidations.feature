Feature:This is a feature file for Place validations....

	@AddPlace 
	Scenario Outline:
		Given I have addPlace API contract with "<Name>" "<Address>" "<Language>" "<Website>"
		When user calls "addPlace" with "POST" http method
		Then expect status code to 200
		Then "status" in response body is "OK"
		And  "scope" in response body is "APP"
		And verify that the "getPlace" with "GET" http method has "<Name>"
		
	Examples:
	| Name         | Address         | Language | Website      |
	| White House  | Washington D.C. | en_US    | google.com   | 
	| London House | London, Britain | en_UK    | google.co.uk |
	
	@DeletePlace 
	Scenario:  Validate deletePlace API is working
		Given I have delete Place API
		When user calls "deletePlace" with "POST" http method
		Then expect status code to 200
		Then "status" in response body is "OK"


