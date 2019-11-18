// 1. feladat keresztekből
class Feladat1 {

  static void Display(int x, int y, int n) {
    for (int i = 0; i < y; ++i) {
      for (int k = 0; k < x; ++k) {

        if ((i + k) % n == 1) {
          System.out.print("#");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println("");
    }

  }

  public static void main(String[] args) {

    Display(6, 8, 5);

  }
}
