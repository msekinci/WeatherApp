package com.example.weatherapp.models;

import java.util.List;

public class ResultsItem{
	private String formatted_address;
	private List<String> types;
	private Geometry geometry;
	private List<AddressComponentsItem> address_components;
	private String place_id;
	private PlusCode plus_code;

	public void setFormattedAddress(String formattedAddress){
		this.formatted_address = formattedAddress;
	}

	public String getFormattedAddress(){
		return formatted_address;
	}

	public void setTypes(List<String> types){
		this.types = types;
	}

	public List<String> getTypes(){
		return types;
	}

	public void setGeometry(Geometry geometry){
		this.geometry = geometry;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public void setAddressComponents(List<AddressComponentsItem> addressComponents){
		this.address_components = addressComponents;
	}

	public List<AddressComponentsItem> getAddressComponents(){
		return address_components;
	}

	public void setPlaceId(String placeId){
		this.place_id = placeId;
	}

	public String getPlaceId(){
		return place_id;
	}

	public void setPlusCode(PlusCode plusCode){
		this.plus_code = plusCode;
	}

	public PlusCode getPlusCode(){
		return plus_code;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"formatted_address = '" + formatted_address + '\'' +
			",types = '" + types + '\'' + 
			",geometry = '" + geometry + '\'' + 
			",address_components = '" + address_components + '\'' +
			",place_id = '" + place_id + '\'' +
			",plus_code = '" + plus_code + '\'' +
			"}";
		}
}