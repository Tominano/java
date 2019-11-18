package tests;

import auction.Auction;
import auction.Lot;

import java.util.*;

import utest.*;

public class Part3AuctionTest extends Testable {
    
	@Override
	public void assertion() {
		Lot[] lots = new Lot[]
		    { Lot.make("Salvador Dali", "MOMENT DE TRANSITION", 6000000)
		    , Lot.make("Pablo Picasso", "PLANT DE TOMATES", 10000000)
		    , Lot.make("Salvador Dali", "ARGOS", 100000)
		    , Lot.make("Marc Chagall", "LA TOUR EIFFEL", 500000)
		    };

			Auction a = new Auction(lots);
			
			// browseLots tesztelese
			List<Lot> list1 = a.browseLots("Joan Miro");
			check("browseLots(): nullt adott, ha nincsenek mutargyai egy muvesznek.", list1 != null);
			check("browseLots(): nem ures listat adott, ha nincsenek mutargyai egy muvesznek.", list1.size()==0);
			List<Lot> list2 = a.browseLots("Salvador Dali");
			check("browseLots(): adott muvesz eseten (ha letezik olyan mutargy, amelyet a muvesz keszitett) nem jol vegzi a keresest.", list2.size()==2 && list2.contains(lots[0]) && list2.contains(lots[2]));
			
			Map<String, Long> prices = new HashMap<>();
			prices.put("Salvador Dali", 6000000l + 100000l);
			prices.put("Marc Chagall", 500000l);
			prices.put("Pablo Picasso", 10000000l);

			// osszegzes
			for (Map.Entry<String, Long> e : prices.entrySet())
				check("priceOfCollection(): rosszul osszegez, ha az alkotonak van legalabb egy muve.", a.priceOfCollection(e.getKey()) == e.getValue().longValue());
			check("priceOfCollection(): rosszul osszegez, ha az alkotonak nincs muve.", a.priceOfCollection("Henry Moore") == 0);

			// a legdragabb mutargy megkeresese
			check("mostExpensive(): a metodus nem jol valasztja ki a legregebbi mutargyat, ha nincsenek mutargyak a listaban.", new Auction(new Lot[0]).mostExpensive() == null);
			Lot lot = a.mostExpensive();
			check("chooseOldest(): a metodus nem jol valasztja ki a legregebbi mutargyat, ha vannak mutargyak a listaban.", lot == lots[1]);
	}
	
	@Override
	public String description() {
		return getClass().getName();
	}
    
	@Override
	public String className() { 
		return "auction.Auction"; 
	}

	@Override
	public Object[] expectedFields() throws Exception {
		return new Object[] {};
	}
	
	@Override
	public Object[] expectedMethods() throws Exception {
		return new Object[]
		    { constructor(className(), Lot[].class)
		    , optionalMethod(Integer.TYPE, className() + ".numberOfLots")
		    , optionalMethod(String.class, className() + ".toString")
		    , method(List.class, className() + ".browseLots", String.class)
		    , method(Long.TYPE, className() + ".priceOfCollection", String.class)
		    , method(Lot.class, className() + ".mostExpensive")
		    };
	}
    
	@Override
	public int score() {  
		return 8; 
	}
    
	public static void main(String... args) {
		Test.main(new Part3AuctionTest());
	}

}
