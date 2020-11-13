package com.project;

public class Hayvan {


	private HayvanTur hayvanTur;
	private Cinsiyet cinsiyet;
	public Hayvan(HayvanTur hayvanTur) {
		this.hayvanTur = hayvanTur;
	}
	public HayvanTur getHayvanTur() {
		return hayvanTur;
	}
	public void setHayvanTur(HayvanTur hayvanTur) {
		this.hayvanTur = hayvanTur;
	}
	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}
	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

}