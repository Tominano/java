package auction;

public class Auction { //ez egy rejtett műtárgy sorozat adattag, magyarul egy TÖMB
	private Lot[] lots;
	
	public Auction(Lot[] lots) { //feltöltöm a tömböt és figyelek a szivárgásra
		this.lots = new Lot[lots.length]; 
		for(int i=0;i<lots.length;++i)
			this.lots[i] = new Lot(lots[i]);
	}
	
	public int numberOfLots() {//Ez a műtárgyak számát adja meg a tömb hossza
		return lots.length;
	}
	
	public String toString() { // a stingbuilderrel csinálok egy stringet amit a lot. ban 
		StringBuilder sb = new StringBuilder();//csinált toString állít elő sortöréssel
		for(Lot lot : lots)
			sb.append(lot.toString()).append('\n');
		return sb.toString();
	}
	
	public Lot[] browseLots(String artist) {
		Lot[] result = new Lot[lots.length]; // létrehozom azt a tömböt amivel vissza kell térnie
		int cnt = 0; // ettől az indextöl számolok elpre
		for(Lot lot : lots) // ":" csinálj vele valamit
			if(lot.getArtist().equals(artist))
				result[cnt++] = lot;
		return result;
	}
	
	public long priceOfCollection(String artist) {
		long result = 0;
		for(Lot lot : lots)
			if(lot.getArtist().equals(artist))
				result += lot.getHammerPrice();
		return result;
	}
	
	public Lot mostExpensive() {
		Lot result = null;
		for(Lot lot : lots)
			if(result == null ||
		       lot.getHammerPrice() > result.getHammerPrice())
			{
				result = lot;
			}
		return result;
	}
}	