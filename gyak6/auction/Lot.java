package auction;
/*Létrehozom a public class-t, hogy amiben létrehozom a változókat*/
public class Lot {
    private String artist, title;
    private int h_price;
/*Ez a konstruktor, ami kimutat a this-el, a változókra, ha te nem hozol létre
konstruktort akor alapértelmezetten létezik egy üres*/
private Lot(String artist, String title, int h_price){
    this.artist = artist;
    this.title = title;
    this.h_price = h_price;
}
public Lot(Lot other){
    artist = other.artist;
    title = other.title;
    h_price = other.h_price;
}

/*Ide definiálok egy osztályszintű metódust!!!
Definiáljunk egy osztályszintű make nevű metódust is. A make metódus szintén az alkotó nevét,
a műalkotás címét és a kikiáltási árát kapja meg paraméterként. A metódus először ellenőrzi,
hogy a paraméterek megfelelőek. Amennyiben igen, akkor létrehozza és visszaadja a
paramétereknek megfelelő Lot típusú objektumot. Ha a paraméterek nem megfelelőek, akkor
a metódus null -t adjon vissza.
Az alkotó neve akkor megfelelő, ha nem egy null referencia.
A műalkotás címe akkor megfelelő, ha szintén nem egy null referencia, és legalább 2
karakter hosszú, csak nagybetűkből és szóközökből áll.
A kikiáltási ár akkor megfelelő, ha pozitív szám.*/
public static Lot make(String artist, String title, int h_price) {
 
      if (artist == null || title == null || title.length() < 2 || h_price <= 0)
        return null;
  
      for (int i = 0; i < title.length(); i++) {
        char c = title.charAt(i);
        if(c != ' ' && !Character.isUpperCase(c))
            return null;
      }
      return new Lot(artist, title, h_price);
}
/*Ezeg a getter függvények amik az eredeti értékekkel tér vissza*/
public String getArtist(){
    return artist;
}
public String getTitle(){
    return title;
}
public int getHammerPrice(){
    return h_price;
}

public void bid(int bidValue){
    h_price = Math.max(h_price, bidValue);
}
/*Ez rakja össze a kiirt stringet*/
public String toString(){
    return artist + ":" + " " + title + "(" + h_price + " GBP)";
}
/*Az és kiértékelés akkor meg áll ha az elsőre hamisat talál és így nem vizsgál tovább, ezért nagyon figyelni kell a sorrendre*/
public boolean moreExpensiveThan(Lot other){
    return other != null && h_price > other.h_price;
}
}