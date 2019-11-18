//  Hello World
class Gyak11 {
  public static void main(String[] args) {
    System.out.println("Hello world");
  }
}

// Println and types
class Gyak12 {
  public static void main(String[] args) {

    int x = 100;
    int y;
    y = 2;
    boolean b = true;
    char c = '#';
    // short, int, long, float, double

    System.out.println(x * x + " " + c);

  }
}

// if, else
class Gyak13 {
  public static void main(String[] args) {

    int y = 2;

    if (y == 3) {
      System.out.println("ketto");
    } else {
      y = 2;
    }

  }
}

// for
class Gyak14 {
  public static void main(String[] args) {

    int i;
    for (i = 0; i < 10; ++i) {
      System.out.println(i);
    }

  }
}

// while
class Gyak15 {
  public static void main(String[] args) {

    int i = 1;
    while (i < 10) {
      System.out.println(i);
      i++;
    }

  }
}

// feladat átlo keresztekből
class Gyak16 {
  public static void main(String[] args) {

    int i, k;
    for (i = 0; i < 10; ++i) {
      for (k = 0; k < 10; ++k) {

        if (k < i) {  
          System.out.print(" ");
        }
        if (k == i) {
          System.out.println("#");
        }

      }
    }

  }
}

// feladat keret keresztekből
class Gyak17 {

  static void teglalap(int x, int y) {
    for (int i = 0; i < x; i++)
      System.out.print("#");
    System.out.println("");

    for (int i = 0; i < (y - 2); i++) {
      for (int j = ; j < x; j++) {
        if (j == 0 || j == (x - 1)) {
          System.out.print("#");
        } else {
          System.out.print(" ");
        }
      }(
      System.out.println("");
    }
    for (int i = 0; i < x; i++) 
         System.out.print("#");

  }

  public static void main(String[] args) {

    teglalap(14, 5);

  }
}

// 1. feladat keresztekből
class Gyak18 {

  static void display(int x, int y, int n) {
    for (int i = 0; i < x; ++i) {
      for (int k = 0; k < y; ++k) {

        if ((n + y) % n == 1) {
          System.out.print("#");
        } else {
          System.out.print("");
        }
        System.out.println("");

      }
    }

  }

  public static void main(String[] args) {

    display(14, 5, 3);

  }
}
