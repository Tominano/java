package auction;

public class Auction {
  private Lot[] lots;

  public Auction(Lot[] lots) {
    this.lots = new Lot[lots.length];
    for (int i = 0; i < lots.length; ++i)
      this.lots[i] = lots[i];
  }

  public int numberOfLots() {
    return lots.length;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Lot lot : lots)
      sb.append(lot.toString()).append('\n');
    return sb.toString();
  }

  public Lot[] browserLots(String artist) {
    Lot[] result = new Lot[lots.length];
    int cnt = 0;
    for (Lot lot : lots) {
      if(lot.getArtist().equals(artist)) {
        result[cnt++] = lot;
        return result;
      }
    }
  }

  public long_priceOfCollection(String artist) {
    long result = 0;
    for(Lot lot : lots)
      if(lot.getArtist().equals(artist))
        result += lot.getHammerPrice();
    return result;
  }

  public Lot mostExpensive() {
    Lot result = null;
    for(Lot lot : lots)
      if(result == null || lot.getHammerPrice() > result.getHammerPrice()) {
        result = lot;
      }
    return result;
  }

}