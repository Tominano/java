package auction;   //mindíg meg kell mondani hogy melyik package-ban van

public class Lot {//"Az osztálynak 3 rejtett adattagja van"
	private String artist, title;
	private int hammerPrice;
	
	private Lot(String artist, String title, int hammerPrice) {// ez a konstruktorunk
		this.artist = artist;   //Amikor oda van írva, hogy legyen egy rejtett konstruktorunk
		this.title = title;    //akkor odaírjuk, hogy private
		this.hammerPrice = hammerPrice;  // a "this." a skope-on kívül deklarált válltozóra mutat vissza
	}										// de csak eggyel fölé mutathatunk
	
	public Lot(Lot other) { //Ez ahhoz  arészhez kell, ahol a liciteket kezeljük
		artist = other.artist; // Minden ilyen részhez kell konstruktor
		title = other.title; // és a szivárgás ellen
		hammerPrice = other.hammerPrice;
	}
	// Ez itt az osztályszintű make metódus első if a hibákat kezeli, a double pipe(||) az OR-t
	// helyttesíti az ÉS-t pedig a && adja.
		public static Lot make(String artist, String title, int hammerPrice) {
		if(artist == null || title == null || title.length() < 2 || hammerPrice <= 0)
			return null; //A NEM-eket vizsgálom vele
		for(int i=0;i<title.length();++i) {
			char c = title.charAt(i);   //Ez a rész azt vizsgálja hogy csak nagybetűböl áll-e
			if(c != ' ' && !Character.isUpperCase(c))  //Nem tartalmaz szóközt és nem nagybetű
				return null;
		}
		return new Lot(artist, title, hammerPrice);// ha minden rendben van akkor legyártja
												   //A feladat leírja, hogy létrehozza és visszaadja NEW
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getTitle() {    //lekérdező metódusok, ezek a getterek
		return title;
	}
	
	public int getHammerPrice() {
		return hammerPrice;
	}
	
	public void bid(int bidValue) {  // nincsen visszatérési értéke, pozitív egészet vár
		hammerPrice = Math.max(hammerPrice, bidValue);//SIMA IF ELGETÉSSEL IS JÓ
	}// ha a paraméter nagyobb, mint műalkotás leütési ára,
	//akkor aleütési árat a paraméterrel tesszük egyenlővé. Különben nem történik semmi.
	
	public String toString() {// összerakok egy mondatot amit a visszatérési érték kap meg
		return artist + ": " + title + "(" + hammerPrice + " GBP)";
	}
	
	public boolean moreExpensiveThan(Lot other) {
		return other != null && hammerPrice > other.hammerPrice;
		
	}
}
