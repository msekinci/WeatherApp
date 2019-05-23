package com.example.weatherapp.models;

import java.util.List;

public class LocationModel{
	private PlusCode plusCode;
	private List<ResultsItem> results;
	private String status;

	public void setPlusCode(PlusCode plusCode){
		this.plusCode = plusCode;
	}

	public PlusCode getPlusCode(){
		return plusCode;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"LocationModel{" + 
			"plus_code = '" + plusCode + '\'' +
			",results = '" + results + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}