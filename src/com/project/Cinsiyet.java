package com.project;

public enum Cinsiyet {

	ERKEK(0,"Erkek"),
	DISI(1,"Diþi");
	
	private int deger;
	private String label;
	private Cinsiyet(int deger, String label) {
		this.deger = deger;
		this.label = label;
	}
	public int getDeger() {
		return deger;
	}
	public void setDeger(int deger) {
		this.deger = deger;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public static Cinsiyet getCinsiyet(int deger) {
		 switch (deger) {
		case 0: 
			return ERKEK;
		case 1: 
			return DISI;
		default:
			break;
		}
		 return ERKEK;
	}
}
