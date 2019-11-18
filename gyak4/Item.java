class Item {
  int id;
  private static int counter;

  public static int getCounter() {
    return counter;
  }

  Item() {
    id = counter++;
  }
}