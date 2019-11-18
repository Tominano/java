package rail;

public class Railway{
  private String city1, city2;
  private int dist;

  public static Railway KESZTHELY_BUDAPEST = new Railway("Keszthely", "Budapest", 190); 

  public Railway(String city1, String city2, int dist){
    this.city1 = city1;
    this.city2 = city2;
    this.dist = dist;
  }

  public String[] getCities(){
    return new String[] {city1, city2};
  }

  public int getDistance() {
    return dist;
  }

  public static Railway make(String railway) {
    String[] railwaypSplit = railway.split(" ");
    if(railwaypSplit.length == 3 && railwaypSplit[0].length() != 0 && railwaypSplit[1].length() != 0) {
      for(int i = 0; i < railwaypSplit[2].length(); ++i){
        if(!Character.isDigit(railwaypSplit[2].charAt(i)))
          return null;
      }
      return new Railway(railwaypSplit[0], railwaypSplit[1], Integer.parseInt(railwaypSplit[2]));
    } else 
      return null;
  }

  public boolean hasEnd(String city){
    return city1.equals(city) || city2.equals(city);
  }

  public String getOtherCity(String city){
    if(city1.equals(city))
      return city2;
    if(city2.equals(city))
      return city1;
    return null;
  }

}