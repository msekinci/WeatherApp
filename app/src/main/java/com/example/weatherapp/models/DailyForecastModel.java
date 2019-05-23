package com.example.weatherapp.models;

import java.util.List;

public class DailyForecastModel{
	private String merkez;
	private String baslangicZamani;
	private int istNo;
	private List<TahminItem> tahmin;

	public void setMerkez(String merkez){
		this.merkez = merkez;
	}

	public String getMerkez(){
		return merkez;
	}

	public void setBaslangicZamani(String baslangicZamani){
		this.baslangicZamani = baslangicZamani;
	}

	public String getBaslangicZamani(){
		return baslangicZamani;
	}

	public void setIstNo(int istNo){
		this.istNo = istNo;
	}

	public int getIstNo(){
		return istNo;
	}

	public void setTahmin(List<TahminItem> tahmin){
		this.tahmin = tahmin;
	}

	public List<TahminItem> getTahmin(){
		return tahmin;
	}

	@Override
 	public String toString(){
		return 
			"DailyForecastModel{" + 
			"merkez = '" + merkez + '\'' + 
			",baslangicZamani = '" + baslangicZamani + '\'' + 
			",istNo = '" + istNo + '\'' + 
			",tahmin = '" + tahmin + '\'' + 
			"}";
		}
}