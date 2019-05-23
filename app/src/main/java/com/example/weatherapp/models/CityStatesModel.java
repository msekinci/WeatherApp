package com.example.weatherapp.models;

public class CityStatesModel{
	private String name;
	private int id;
	private int state;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setState(int state){
		this.state = state;
	}

	public int getState(){
		return state;
	}

	@Override
 	public String toString(){
		return 
			"CityStatesModel{" + 
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",state = '" + state + '\'' + 
			"}";
		}
}
