package com.example.weatherapp.models;

public class TahminItem{
	private String tarih;
	private int nem;
	private int sicaklik;
	private int ruzgarHizi;
	private int hissedilenSicaklik;
	private int maksimumRuzgarHizi;
	private String hadise;
	private int ruzgarYonu;

	public void setTarih(String tarih){
		this.tarih = tarih;
	}

	public String getTarih(){
		return tarih;
	}

	public void setNem(int nem){
		this.nem = nem;
	}

	public int getNem(){
		return nem;
	}

	public void setSicaklik(int sicaklik){
		this.sicaklik = sicaklik;
	}

	public int getSicaklik(){
		return sicaklik;
	}

	public void setRuzgarHizi(int ruzgarHizi){
		this.ruzgarHizi = ruzgarHizi;
	}

	public int getRuzgarHizi(){
		return ruzgarHizi;
	}

	public void setHissedilenSicaklik(int hissedilenSicaklik){
		this.hissedilenSicaklik = hissedilenSicaklik;
	}

	public int getHissedilenSicaklik(){
		return hissedilenSicaklik;
	}

	public void setMaksimumRuzgarHizi(int maksimumRuzgarHizi){
		this.maksimumRuzgarHizi = maksimumRuzgarHizi;
	}

	public int getMaksimumRuzgarHizi(){
		return maksimumRuzgarHizi;
	}

	public void setHadise(String hadise){
		this.hadise = hadise;
	}

	public String getHadise(){
		return hadise;
	}

	public void setRuzgarYonu(int ruzgarYonu){
		this.ruzgarYonu = ruzgarYonu;
	}

	public int getRuzgarYonu(){
		return ruzgarYonu;
	}

	@Override
 	public String toString(){
		return 
			"TahminItem{" + 
			"tarih = '" + tarih + '\'' + 
			",nem = '" + nem + '\'' + 
			",sicaklik = '" + sicaklik + '\'' + 
			",ruzgarHizi = '" + ruzgarHizi + '\'' + 
			",hissedilenSicaklik = '" + hissedilenSicaklik + '\'' + 
			",maksimumRuzgarHizi = '" + maksimumRuzgarHizi + '\'' + 
			",hadise = '" + hadise + '\'' + 
			",ruzgarYonu = '" + ruzgarYonu + '\'' + 
			"}";
		}
}
