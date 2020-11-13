package com.project;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		int adim=1000;
		int adimSay=0;
		HashMap<String, Hayvan> hayvanList = new HashMap<String, Hayvan>();
		Methods method = new Methods();
		HayvanTur tur = HayvanTur.KOYUN;
		method.hayvanOlustur(tur,hayvanList);
		tur=HayvanTur.INEK;
		method.hayvanOlustur(tur,hayvanList);
		tur=HayvanTur.TAVUK;
		method.hayvanOlustur(tur,hayvanList);
		tur=HayvanTur.KURT;
		method.hayvanOlustur(tur,hayvanList);
		tur=HayvanTur.HOROZ;
		method.hayvanOlustur(tur,hayvanList);
		tur=HayvanTur.ASLAN;
		method.hayvanOlustur(tur,hayvanList);
		tur=HayvanTur.AVCI;
		method.hayvanOlustur(tur,hayvanList);

		while (adim>0) {
			adimSay=method.hareketEt(hayvanList);
			adim-=adimSay;

		}
		method.goster(hayvanList);
	}

}
