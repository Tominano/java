package auction;

public class Lot {

	private String artist, title;
	private int hammerPrice;
	
	private Lot(String artist, String title, int hammerPrice) {

		this.artist = artist;
		this.title = title;
		this.hammerPrice = hammerPrice;
	}
	
	public static Lot make(String artist, String title, int hammerPrice) {

		if(artist == null || title == null || title.length() < 2 || hammerPrice <= 0)
			return null;

		for(int i=0;i<title.length();++i) {
			char c = title.charAt(i);

			if(c != ' ' && !Character.isUpperCase(c))

				return null;

		}
		return new Lot(artist, title, hammerPrice);
	}

	
}
