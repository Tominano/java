import auction.Auction;
import auction.Lot;

class Main{
     public static void main(String[] args){   
        Lot[] lots = new Lot[1];
        lots[0] = Lot.make("asd","ASD",2222);
        Auction auction = new Auction(lots);
        System.out.println(auction);
     }
    }
