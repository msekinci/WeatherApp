package com.example.weatherapp.models;

public class PlusCode{
	private String compound_code;
	private String global_code;

	public void setCompoundCode(String compoundCode){
		this.compound_code = compoundCode;
	}

	public String getCompoundCode(){
		return compound_code;
	}

	public void setGlobalCode(String globalCode){
		this.global_code = globalCode;
	}

	public String getGlobalCode(){
		return global_code;
	}

	@Override
 	public String toString(){
		return 
			"PlusCode{" + 
			"compound_code = '" + compound_code + '\'' +
			",global_code = '" + global_code + '\'' +
			"}";
		}
}
