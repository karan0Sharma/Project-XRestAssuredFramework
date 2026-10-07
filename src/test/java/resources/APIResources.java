package resources;

public enum APIResources {
	
	// enum is a special class in java that has a collection of methods or constants..
	// each method is defined simply by method-name(argument)...
	// if we have multiple methods then each method should be seperated by , and apply ; in the end
	
	addPlace("/maps/api/place/add/json"),
	getPlace("/maps/api/place/get/json"),
	updatePlace(""),
	deletePlace("/maps/api/place/delete/json");

	private String resource;
	
	APIResources(String resource) 
	{
		this.resource = resource;
		
	}
	
	
	public String getResource()
	{
		return resource;
	}
	

}




