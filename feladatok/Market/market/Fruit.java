package market;

import javax.lang.model.util.ElementScanner6;

public class Fruit {
  private String name;
  private int price;

  private Fruit(String name, int price) {
    this.name = name;
    this.price = price;
    if(cheapestFruit == null || price < cheapestFruit.price){
      cheapestFruit = this;
    }
  }

  public static Fruit make(String name, int price) {
    if (name.length() >= 2 && name.matches("[a-zA-Z]+") && 0 < price && price < 5000 && (price % 5) == 0) {
        return new Fruit(name, price);
    } else
        return null;
  }

  public int getPrice() {
    return price;
  }

  public boolean cheaperThan(Fruit x){
    if(x.price > price){
      return true;
    } else
      return false;
  }

  public String show(){
    if(price >= 1000){
      String priceString = "" + price;
      char firstLetterchar = priceString.charAt(0);
      return name + " (" + firstLetterchar + " " + priceString.substring(1) + " Ft)";
    } else
      return name + " (" + price + " Ft)";
  }

  private static Fruit cheapestFruit = null;


  public static Fruit getCheapestFruit() {
    return cheapestFruit;
  }

}