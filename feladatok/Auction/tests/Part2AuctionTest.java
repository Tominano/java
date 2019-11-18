package tests;

import auction.Auction;
import auction.Lot;

import java.util.*;

import utest.*;

public class Part2AuctionTest extends Testable {
    
	@Override
	public void assertion() {
		Lot[] lots = new Lot[]
			{ Lot.make("Henri Matisse", "JACQUY", 350000)
		    , Lot.make("Pablo Picasso", "PLANT DE TOMATES", 10000000)
		    , Lot.make("Marc Chagall", "LA TOUR EIFFEL", 500000)
			};
		
		Auction a = new Auction(lots);
		
		check("numberOfLots: nem megfelelo szamot adja vissza.", a.numberOfLots() == lots.length);

		Lot l = lots[0];
		// adatszivargas ellenorzese
		String s = a.toString();
		lots[0] = Lot.make("Edvard Munch", "THE SCREAM", 1000000);
		check("konstruktor: szivarog a belso allapot.", !s.contains(lots[0].toString()));

		lots[0] = l;

		// helyes-e a szoveges reprezentacio
		for (Lot lot : lots)
			check("toString: hianyzik mualkotas. (" + lot.getTitle() + ")", s.contains(lot.toString()));
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
		    , method(Integer.TYPE, className() + ".numberOfLots")
		    , method(String.class, className() + ".toString")
		    , optionalMethod(List.class, className() + ".browseLots", String.class)
		    , optionalMethod(Long.TYPE, className() + ".priceOfCollection", String.class)
		    , optionalMethod(Lot.class, className() + ".mostExpensive")
		    };
	}
    
	@Override
	public int score() {  
		return 5; 
	}
    
	public static void main(String... args) {
		Test.main(new Part2AuctionTest());
	}

}
