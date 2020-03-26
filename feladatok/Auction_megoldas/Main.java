import auction.Auction;
import auction.Lot;

class Main {
	
	public static void main(String[] args) {
		Lot[] lots = new Lot[1];
		String artist = "asd";
		lots[0] = Lot.make(artist, "ASD", 999999);
		Auction auction = new Auction(lots);
		System.out.println(auction);
		//lots[0].bid(6666666);
		artist += "f";
		System.out.println(auction);
	}
}