// Statikusságra

class Gyak4Statikussag {
  public static void main(String[] args) {

    Item i0 = new Item();
    Item i1 = new Item();
    Item i2 = new Item();

    System.out.println(i2.id);
    // Példány szintu contexus lehet hivatkozni a statikus mezokre (globális
    // counterre)
    System.out.println(Item.getCounter());
    // Példányon keresztül is ellehet írni statikus mezot, de ilyet nem csinálunk
    System.out.println(i2.getCounter()); // <-- Egy elemen keresztül ne hivatkozzunk globálisra
  }
}