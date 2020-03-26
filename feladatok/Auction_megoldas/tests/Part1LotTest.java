package tests;

import auction.Lot;
import utest.*;

public class Part1LotTest extends Testable {
    
	@Override
	public void assertion() {
		// a muvesz nevenek ellenorzese
		Lot l1 = Lot.make(null, "PORTRAIT DE MADAME DUCAS", 400000); 
		check("Lot.make(): letrehozza az objektumot, ha a nev egy null referencia.", l1 == null);

		l1 = Lot.make("Salvador Dali", null, 400000); 
		check("Lot.make(): letrehozza az objektumot, ha a cim egy null referencia.", l1 == null);
		
		// a mualkotas cimenek ellenorzese
		l1 = Lot.make("Salvador Dali", "P", 400000);
		check("Lot.make(): tul rovid cim eseten is letrehozza az objektumot.", l1 == null);
		l1 = Lot.make("Salvador Dali", "Portrait de Madame Ducas", 400000);
		check("Lot.make(): akkor is letrehozza az objektumot, ha a cim nem kisbetuket is tartalmaz.", l1 == null);
		l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAM,E DUCAS", 400000);
		check("Lot.make(): vesszot tartalmazo cimnel is letrehozza az objektumot.", l1 == null);
		
		// az ar ellenorzese
		l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAME DUCAS", -400000);
		check("Lot.make(): negativ kikialtasi arnal is letrehozza az objektumot.", l1 == null);
		l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAME DUCAS", 0);
		check("Lot.make(): nulla kikialtasi arnal is letrehozza az objektumot.", l1 == null);
		
		// jol megadott parameterek eseten letre kell hozni az objektumot

		l1 = Lot.make("Salvador Dali", "PORTRAIT DE MADAME DUCAS", 400000);
		check("Lot.make(): helyes parameterekkel sem hozza letre az objektumot.", l1 != null);
		
		// helyes muvesznevet kapunk-e vissza
		check("getArtist(): a metodus nem adja vissza helyesen a muvesz nevet.", l1.getArtist().equals("Salvador Dali"));
		
		// helyesen adjuk-e vissza a leutesi arat
		check("getHammerPrice(): a metodus nem a helyes leutesi arat adja vissza, ha nem tortent licitalas.", l1.getHammerPrice() == 400000);
		
		int price = l1.getHammerPrice();
		// helyesen tortenik-e a licitalas
		l1.bid(l1.getHammerPrice() - 1);
		check("bid(): akkor is modosit az aron, ha a parameter alacsonyabb az arnal.", l1.getHammerPrice() == price);
		l1.bid(l1.getHammerPrice() + 1);
		check("bid(): nem modosit az aron, ha a parameter nagyobb az arnal.", l1.getHammerPrice() == price + 1);
		
		// a helyes szoveges reprezentaciot kapjuk-e vissza
		String s = l1.toString();
		check("toString(): a metodus nem a helyes szoveges reprezentaciot adja vissza. (szerzo)", s.contains("Salvador Dali"));

		check("toString(): a metodus nem a helyes szoveges reprezentaciot adja vissza. (cim)", s.contains("PORTRAIT DE MADAME DUCAS"));
		check("toString(): a metodus nem a helyes szoveges reprezentaciot adja vissza. (ar)", s.contains((price + 1) + " GBP"));

		// melyik a dragabb?
		Lot l2 = Lot.make("Roberto Matta", "AZUL", 120000);
		check("moreExpensiveThan(): hamisat ad vissza, amikor a parameter mutargy az olcsobb.", l1.moreExpensiveThan(l2));
		check("moreExpensiveThan(): igazat ad vissza, amikor a parameter mutargy a dragabb.", !l2.moreExpensiveThan(l1));
		check("moreExpensiveThan(): igazat ad, amikor a parameter null.", !l1.moreExpensiveThan(null));

		l2 = Lot.make("Roberto Matta", "AZUL", l1.getHammerPrice());
		check("moreExpensiveThan(): igazat ad vissza, amikor a parameter mutargy ugyanannyiba kerul.", !l2.moreExpensiveThan(l1));
	}
	
	@Override
	public String description() {
		return getClass().getName();
	}
    
	@Override
	public String className() { 
		return "auction.Lot"; 
	}

	@Override
	public Object[] expectedFields() throws Exception {
		return new Object[] { };
	}
	
	@Override
	public Object[] expectedMethods() throws Exception {
		return new Object[]
			{ staticMethod(Lot.class, className() + ".make", String.class, String.class, Integer.TYPE)
			, method(String.class, className() + ".getArtist")
			, method(String.class, className() + ".getTitle")
			, method(Integer.TYPE, className() + ".getHammerPrice")
			, method(Boolean.TYPE, className() + ".moreExpensiveThan", Lot.class)
			, method(Void.TYPE, className() + ".bid", Integer.TYPE)
			, method(String.class, className() + ".toString")
			};
	}
    
	@Override
	public int score() {  
		return 10; 
	}
    
	public static void main(String... args) {
		Test.main(new Part1LotTest());
	}

}
