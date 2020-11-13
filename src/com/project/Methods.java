package com.project;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Methods {

	final static int boyut = 500;

	public void hayvanOlustur(HayvanTur hTur, HashMap<String, Hayvan> hayvanList) {
		HayvanTur tur=HayvanTur.getHayvanTur(hTur.getLabel());
		tur.setSayi(hTur.getSayi());
		for (int i = 0; i < tur.getSayi(); i++) {
			String key=degerKontrol(hayvanList);
			Hayvan hayvan = new Hayvan(tur);
			if(tur.getLabel().equals("Horoz"))
				hayvan.setCinsiyet(Cinsiyet.ERKEK);
			else if(tur.getLabel().equals("Tavuk") )
				hayvan.setCinsiyet(Cinsiyet.DISI);
			else if ((tur.getSayi() / 2) > i && tur.getSayi() != 1 )
				hayvan.setCinsiyet(Cinsiyet.DISI);
			else if (tur.getSayi() != 1)
				hayvan.setCinsiyet(Cinsiyet.ERKEK);
			else
				hayvan.setCinsiyet(randomCinsiyet());

			hayvanList.put(key, hayvan);
			uremeKontrol(key, hayvanList);
			avKontrol(hayvan,hayvanList,key);
		}
	}

	public String randomSayi() { 

		Random rand = new Random();
		int x = rand.nextInt(boyut);
		int y = rand.nextInt(boyut);
		return x+"-"+y;
	}

	public String degerKontrol(HashMap<String, Hayvan> hayvanList) {
		String key=randomSayi();
		Hayvan hayvan=hayvanList.get(key);
		if(hayvan != null)
			return degerKontrol(hayvanList);
		return key;
	}
	public Key getKey(String keystr) {
		Key key=new Key();
		String[] value= keystr.split("-");
		key.setX(Integer.parseInt(value[0]));
		key.setY(Integer.parseInt(value[1]));
		return key;
	}
	public void uremeKontrol(String keystr, HashMap<String, Hayvan> hayvanList) {
		Key yeniKey = new Key();
		Key key=getKey(keystr);
		String yeniKeyStr="";
		Hayvan hayvan =  hayvanList.get(keystr) ;
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 2;j++) {
				if(i!=0 || j!=0 ) {
					yeniKey.setX(key.getX());
					yeniKey.setY(key.getY());

					if ((key.getX() - i) >= 0 && (key.getY() - j) >= 0) {
						yeniKey.setX(key.getX() - i);
						yeniKey.setY(key.getY() - j);
						yeniKeyStr=yeniKey.getX()+"-"+yeniKey.getY();
						Hayvan hayvanKontrol =   hayvanList.get(yeniKeyStr);
						uremeTurCinsiyetKontrol(hayvan, hayvanKontrol,hayvanList);
					}
					if ((key.getX() + i) <= (boyut-1) && (key.getY() + i) <= (boyut-1)) {
						yeniKey.setX(key.getX() + i);
						yeniKey.setY(key.getY() + j);
						yeniKeyStr=yeniKey.getX()+"-"+yeniKey.getY();
						Hayvan hayvanKontrol = hayvanList.get(yeniKeyStr);
						uremeTurCinsiyetKontrol(hayvan, hayvanKontrol,hayvanList);
					}
				}

			}
		}
	}

	public void uremeTurCinsiyetKontrol(Hayvan hayvan, Hayvan hayvanKontrol,HashMap<String, Hayvan> hayvanList) {


		if(hayvanKontrol != null
				&& hayvanKontrol.getHayvanTur().getLabel().equals("Tavuk")
				&& hayvan.getHayvanTur().getLabel().equals("Horoz") ) {
 			turKontrol(randomCinsiyet(),hayvanList);
		}
		else if(hayvanKontrol != null
				&& hayvanKontrol.getHayvanTur().getLabel().equals("Horoz")
				&& hayvan.getHayvanTur().getLabel().equals("Tavuk")) {

 			turKontrol(randomCinsiyet(),hayvanList);
		}
		else if (hayvanKontrol != null
				&& hayvanKontrol.getHayvanTur().getLabel().equals( hayvan.getHayvanTur().getLabel())
				&& hayvan.getCinsiyet() != hayvanKontrol.getCinsiyet()) {
			hayvan.getHayvanTur().setSayi(1); // ureme sonucu bir hayvan olusacagýndan sayiya set ediyoruz.
			hayvanOlustur(hayvan.getHayvanTur(), hayvanList);
		}
	}

	public void turKontrol(Cinsiyet cinsiyet,HashMap<String, Hayvan> hayvanList ) {
		HayvanTur yeniHayvanTur=HayvanTur.HOROZ;
		if(cinsiyet.equals(Cinsiyet.ERKEK)) {
			yeniHayvanTur=HayvanTur.HOROZ;
		}else if(cinsiyet.equals(Cinsiyet.DISI)) {
			yeniHayvanTur=HayvanTur.TAVUK;
		}
		yeniHayvanTur.setSayi(1);
		hayvanOlustur(yeniHayvanTur, hayvanList);
	}

	public Cinsiyet randomCinsiyet() {
		Random rand = new Random();
		return Cinsiyet.getCinsiyet(rand.nextInt(2));
	}

	public void avKontrol(Hayvan hayvan, HashMap<String, Hayvan> hayvanList,String keyStr) {
		Key yeniKey = new Key();
		Key key =getKey(keyStr);
		yeniKey.setX(key.getX());
		yeniKey.setY(key.getY());
		String yeniKeyStr="";
		for (int i = 1; i <= hayvan.getHayvanTur().getAvciBirim(); i++) {
			Hayvan hayvanKontrol = null;

			if ((key.getX() - i) >= 0) {
				yeniKey.setX(key.getX() - i);
				yeniKeyStr=yeniKey.getX()+"-"+yeniKey.getY();
				hayvanKontrol =    hayvanList.get(yeniKeyStr);//hayvanlistesi icindeki bu keye karsýlýk gelen hayvan nesnesini aliyoruz
				if (hayvanKontrol != null && hayvan.getHayvanTur().getAvlananHayvan().contains(hayvanKontrol.getHayvanTur().getLabel()))
					hayvanList.remove(yeniKeyStr);
			}
			if ((key.getX() + i) <= ((boyut-1))) {
				yeniKey.setX(key.getX() + i);
				yeniKeyStr=yeniKey.getX()+"-"+yeniKey.getY();
				hayvanKontrol =    hayvanList.get(yeniKeyStr);
				if (hayvanKontrol != null && hayvan.getHayvanTur().getAvlananHayvan().contains(hayvanKontrol.getHayvanTur().getLabel()))
					hayvanList.remove(yeniKeyStr);
			}
			if ((key.getY() - i) >= 0) {
				yeniKey.setY(key.getY() - i);
				yeniKeyStr=yeniKey.getX()+"-"+yeniKey.getY();
				hayvanKontrol =    hayvanList.get(yeniKeyStr);
				if (hayvanKontrol != null && hayvan.getHayvanTur().getAvlananHayvan().contains(hayvanKontrol.getHayvanTur().getLabel()))
					hayvanList.remove(yeniKeyStr);
			}
			if ((key.getY() + i) <= ((boyut-1))) {
				yeniKey.setY(key.getY() + i);
				yeniKeyStr=yeniKey.getX()+"-"+yeniKey.getY();
				hayvanKontrol =    hayvanList.get(yeniKeyStr);
				if (hayvanKontrol != null && hayvan.getHayvanTur().getAvlananHayvan().contains(hayvanKontrol.getHayvanTur().getLabel()))
					hayvanList.remove(yeniKeyStr);
			}
		}
	}

	public int  hareketEt( HashMap<String, Hayvan> hayvanList) {
		int adimSay=0;
		HashMap<String, Hayvan> tempHayvanList =new HashMap<String, Hayvan>();
		tempHayvanList.putAll(hayvanList);
		Iterator<String>  entries = tempHayvanList.keySet().iterator();
		while (entries.hasNext()) {
			String key=entries.next();
			Hayvan hareketHayvan =tempHayvanList.get(key);

			String yeniKonum=yeniKonumBul(key,hareketHayvan.getHayvanTur().getHareketBirim(),hayvanList);
			if(yeniKonum != null) {
				hayvanList.remove(key);
				hayvanList.put(yeniKonum, hareketHayvan);
				uremeKontrol(yeniKonum, hayvanList);
				avKontrol(hareketHayvan,hayvanList,yeniKonum);
				adimSay+= hareketHayvan.getHayvanTur().getHareketBirim();
			}

		}
		return adimSay;
	}

	public String yeniKonumBul(String keyStr,int hareketBirim,HashMap<String, Hayvan> hayvanList) {
		Random rand = new Random();
		Key key=getKey(keyStr);
		int yon=rand.nextInt(3);
		Key yeniKonum=new Key();
		yeniKonum.setX(key.getX());
		yeniKonum.setY(key.getY());
		String yeniKonumStr="";
		switch (yon) {
			case 0:    //yukari
				yeniKonum.setX(key.getX()-hareketBirim);
				yeniKonumStr=yeniKonum.getX()+"-"+yeniKonum.getY();
				if(key.getX()-hareketBirim>0 && konumBosmu(hayvanList,yeniKonumStr)) {
					return yeniKonumStr;
				}
			case 1:   //asagi
				yeniKonum.setX(key.getX()+hareketBirim);
				yeniKonumStr=yeniKonum.getX()+"-"+yeniKonum.getY();
				if(key.getX()+hareketBirim<=(boyut-1) && konumBosmu(hayvanList,yeniKonumStr)) {
					return yeniKonumStr;
				}

			case 2: // sola
				yeniKonum.setX(key.getX());
				yeniKonum.setY(key.getY()-hareketBirim);
				yeniKonumStr=yeniKonum.getX()+"-"+yeniKonum.getY();
				if(key.getY()-hareketBirim>0 && konumBosmu(hayvanList, yeniKonumStr)) {
					return yeniKonumStr;
				}
			case 3:  //saga
				yeniKonum.setX(key.getX());
				yeniKonum.setY(key.getY()+hareketBirim);
				yeniKonumStr=yeniKonum.getX()+"-"+yeniKonum.getY();
				if(key.getY()+hareketBirim<=(boyut-1) && konumBosmu(hayvanList, yeniKonumStr)) {
					return yeniKonumStr;

				}
			default:
				return null;

		}
	}

	public boolean konumBosmu(HashMap<String, Hayvan> hayvanList,String key) {
		Hayvan hareketHayvan = hayvanList.get(key)  ;
		if(hareketHayvan != null)
			return false;
		return true;
	}

	public void goster(HashMap<String, Hayvan> hayvanList) {
		String hayvanTur="";
		Integer sayi=1;
		HashMap<String, Integer> hayvanSayi = new HashMap<String,Integer>();
		for (HashMap.Entry<String, Hayvan> entry  : hayvanList.entrySet()) {
			hayvanTur=entry.getValue().getHayvanTur().getLabel();
			sayi= hayvanSayi.get(hayvanTur);
			if(sayi != null)
				sayi++;
			else
				sayi=1;
			hayvanSayi.put(hayvanTur,sayi);

		}
		//Turkce karakterler sikinti yaptiðindan yorum satirlarinda ve ekrana yazdirmada turkce karakterler kullanilamadi
		System.out.println("Ciftlik Nufusu");
		for(HashMap.Entry<String,Integer> turSayi : hayvanSayi.entrySet()) {
			System.out.println("Hayvan Turu: " + turSayi.getKey() + " Sayisi: " + turSayi.getValue());
		}
	}

}