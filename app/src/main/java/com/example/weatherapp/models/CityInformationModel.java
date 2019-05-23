package com.example.weatherapp.models;

public class CityInformationModel{
	private double boylam;
	private String il;
	private Object alternatifHadiseIstNo;
	private int modelId;
	private int oncelik;
	private int saatlikTahminIstNo;
	private double enlem;
	private int ilPlaka;
	private int gps;
	private int merkezId;
	private String aciklama;
	private String ilce;
	private int gunlukTahminIstNo;
	private int sondurumIstNo;
	private int yukseklik;

	public void setBoylam(double boylam){
		this.boylam = boylam;
	}

	public double getBoylam(){
		return boylam;
	}

	public void setIl(String il){
		this.il = il;
	}

	public String getIl(){
		return il;
	}

	public void setAlternatifHadiseIstNo(Object alternatifHadiseIstNo){
		this.alternatifHadiseIstNo = alternatifHadiseIstNo;
	}

	public Object getAlternatifHadiseIstNo(){
		return alternatifHadiseIstNo;
	}

	public void setModelId(int modelId){
		this.modelId = modelId;
	}

	public int getModelId(){
		return modelId;
	}

	public void setOncelik(int oncelik){
		this.oncelik = oncelik;
	}

	public int getOncelik(){
		return oncelik;
	}

	public void setSaatlikTahminIstNo(int saatlikTahminIstNo){
		this.saatlikTahminIstNo = saatlikTahminIstNo;
	}

	public int getSaatlikTahminIstNo(){
		return saatlikTahminIstNo;
	}

	public void setEnlem(double enlem){
		this.enlem = enlem;
	}

	public double getEnlem(){
		return enlem;
	}

	public void setIlPlaka(int ilPlaka){
		this.ilPlaka = ilPlaka;
	}

	public int getIlPlaka(){
		return ilPlaka;
	}

	public void setGps(int gps){
		this.gps = gps;
	}

	public int getGps(){
		return gps;
	}

	public void setMerkezId(int merkezId){
		this.merkezId = merkezId;
	}

	public int getMerkezId(){
		return merkezId;
	}

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setIlce(String ilce){
		this.ilce = ilce;
	}

	public String getIlce(){
		return ilce;
	}

	public void setGunlukTahminIstNo(int gunlukTahminIstNo){
		this.gunlukTahminIstNo = gunlukTahminIstNo;
	}

	public int getGunlukTahminIstNo(){
		return gunlukTahminIstNo;
	}

	public void setSondurumIstNo(int sondurumIstNo){
		this.sondurumIstNo = sondurumIstNo;
	}

	public int getSondurumIstNo(){
		return sondurumIstNo;
	}

	public void setYukseklik(int yukseklik){
		this.yukseklik = yukseklik;
	}

	public int getYukseklik(){
		return yukseklik;
	}

	@Override
 	public String toString(){
		return 
			"CityInformationModel{" + 
			"boylam = '" + boylam + '\'' + 
			",il = '" + il + '\'' + 
			",alternatifHadiseIstNo = '" + alternatifHadiseIstNo + '\'' + 
			",modelId = '" + modelId + '\'' + 
			",oncelik = '" + oncelik + '\'' + 
			",saatlikTahminIstNo = '" + saatlikTahminIstNo + '\'' + 
			",enlem = '" + enlem + '\'' + 
			",ilPlaka = '" + ilPlaka + '\'' + 
			",gps = '" + gps + '\'' + 
			",merkezId = '" + merkezId + '\'' + 
			",aciklama = '" + aciklama + '\'' + 
			",ilce = '" + ilce + '\'' + 
			",gunlukTahminIstNo = '" + gunlukTahminIstNo + '\'' + 
			",sondurumIstNo = '" + sondurumIstNo + '\'' + 
			",yukseklik = '" + yukseklik + '\'' + 
			"}";
		}
}
