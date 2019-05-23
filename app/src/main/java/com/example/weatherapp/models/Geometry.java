package com.example.weatherapp.models;

public class Geometry{
	private Viewport viewport;
	private Bounds bounds;
	private Location location;
	private String location_type;

	public void setViewport(Viewport viewport){
		this.viewport = viewport;
	}

	public Viewport getViewport(){
		return viewport;
	}

	public void setBounds(Bounds bounds){
		this.bounds = bounds;
	}

	public Bounds getBounds(){
		return bounds;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setLocationType(String locationType){
		this.location_type = locationType;
	}

	public String getLocationType(){
		return location_type;
	}

	@Override
 	public String toString(){
		return 
			"Geometry{" + 
			"viewport = '" + viewport + '\'' + 
			",bounds = '" + bounds + '\'' + 
			",location = '" + location + '\'' + 
			",location_type = '" + location_type + '\'' +
			"}";
		}
}
