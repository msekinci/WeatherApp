package com.example.weatherapp.models;

import java.util.List;

public class AddressComponentsItem{
	private List<String> types;
	private String short_name;
	private String long_name;

	public void setTypes(List<String> types){
		this.types = types;
	}

	public List<String> getTypes(){
		return types;
	}

	public void setShortName(String shortName){
		this.short_name = shortName;
	}

	public String getShortName(){
		return short_name;
	}

	public void setLongName(String longName){
		this.long_name = longName;
	}

	public String getLongName(){
		return long_name;
	}

	@Override
 	public String toString(){
		return 
			"AddressComponentsItem{" + 
			"types = '" + types + '\'' + 
			",short_name = '" + short_name + '\'' +
			",long_name = '" + long_name + '\'' +
			"}";
		}
}