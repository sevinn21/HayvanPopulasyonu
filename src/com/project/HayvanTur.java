package com.project;


public enum HayvanTur {

	KOYUN(0,"Koyun",30,2,0,""),
	INEK(1,"Inek",10,2,0,""),
	TAVUK(2,"Tavuk",10,1,0,""),
	KURT(3,"Kurt",10,3,4,"Koyun,Tavuk,Horoz"),
	HOROZ(4,"Horoz",10,1,0,""),
	ASLAN(5,"Aslan",8,4,5,"Inek,Koyun"),
	AVCI(6,"Avcý",1,1,8,"Koyun,Inek,Tavuk,Kurt,Horoz,Aslan");

	private int deger;
	private String label;
	private int sayi;
	private int hareketBirim;
	private int avciBirim;
	private String avlananHayvan;
	private HayvanTur(int deger, String label,int sayi, int hareketBirim, int avciBirim ,String avlananHayvan) {
		this.deger = deger;
		this.label = label;
		this.sayi=sayi;
		this.hareketBirim = hareketBirim;
		this.avciBirim = avciBirim;
		this.avlananHayvan=avlananHayvan;
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


	public int getSayi() {
		return sayi;
	}
	public void setSayi(int sayi) {
		this.sayi = sayi;
	}
	public int getHareketBirim() {
		return hareketBirim;
	}
	public void setHareketBirim(int hareketBirim) {
		this.hareketBirim = hareketBirim;
	}
	public int getAvciBirim() {
		return avciBirim;
	}
	public void setAvciBirim(int avciBirim) {
		this.avciBirim = avciBirim;
	}


	public String getAvlananHayvan() {
		return avlananHayvan;
	}
	public void setAvlananHayvan(String avlananHayvan) {
		this.avlananHayvan = avlananHayvan;
	}
	public static HayvanTur getHayvanTur(String tur) {
		switch (tur) {
			case "Koyun":
				return KOYUN;

			case "Inek":
				return INEK;

			case "Tavuk":
				return TAVUK;

			case "Kurt":
				return KURT;

			case "Horoz":
				return HOROZ;

			case "Aslan":
				return ASLAN;

			case "Avcý":
				return AVCI;


			default:
				break;
		}
		return KOYUN;
	}

}
