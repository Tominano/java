import rail.Railway;
import rail.RailMap;


import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class Justtestin {
  public static void main(String[] args) throws IOException {

    Scanner in = new Scanner(new File("railmap.txt"));
    LinkedList<String> lst = new LinkedList<>();
    while(in.hasNext()) {
        lst.add(in.nextLine());
    }
    RailMap r = new RailMap("Magyarorszag", lst.toArray(new String[lst.size()]));
    System.out.println(r.capitalCity());
    // A a = new A(6);
    // B b = new B(4, 6);
    // System.out.println(a.f() + " " + b.f(3.5));
    // RailMap railmap = new RailMap(country, {"Szigliget", "Salakszentmotoros", "Bubanatvolgy", "Budapest", "Siofok", "Keszthely"})
    
    // A a = new A(5);
    // System.out.println(a.f(3.5));

    // B b = new B(4, 7);
    // System.out.println(b.f(3.5));
  }
}