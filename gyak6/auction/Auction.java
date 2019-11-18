package auction;

import auction.Lot;

public class Auction{
    private Lot[] lots;

    public Auction(Lot[] lots){
        this.lots = new Lot[lots.length];
        for(int i = 0;i < lots.length; ++i)
            this.lots[i] = new Lot(lots[i]);
    }
    public int numberOfLots(int num){
        num = lots.length;
        return num;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Lot lot : lots){
            sb.append(lot.toString()).append('\n');
        }
        return sb.toString();
    }
}

    public Lot [] browseLots(String artist){
        for (int i = 0; i < num; ++i){
            if(lots[i].equals(SZAR))
                return found[lots[i]];
        }else{
            return lots[null];
        }
    }
/*
    private static priceOfCollection(){
        for (int i = 0; i < num; i++){
            if(lots[i] == search_artist)
                return result += lots[i];
        } 
    }

    private static mostExpensive(){
        if (lots[2]!=0){
            System.out.println(Math.max(lots[2]));
        }else{
            return null;
        }
    }*/