package auction;

public class Lot {

  private String artist, title;
  private int price;

  private Lot(String artist, String title, int price) {
    this.artist = artist;
    this.title = title;
    this.price = price;
  }

  public Lot(Lot other) {
    artist = other.artist;
    title = other.title;
    price = other.price;
  }

  public static Lot make(String artist, String title, int price) {

    if (artist == null || title == null || title.length() < 2 || price <= 0)
      return null;

    for (int i = 0; i < title.length(); i++) {
      if (title.charAt(i) != ' ' && !Character.isUpperCase(title.charAt(i)))
        return null;
    }
    return new Lot(artist, title, price);
  }

  public String getArtist() {
    return artist;
  }

  public String getTitle() {
    return title;
  }

  public int getHammerPrice() {
    return price;
  }

  public void bid(int bidValue) {
    price = Math.max(price, bidValue);
  }

  public String toString() {
    return artist + ": " + title + "(" + price + " GBP)";
  }

  public boolean moreExpensiveThan(Lot other) {
    return other != null && this.price > other.price;
  }
}
