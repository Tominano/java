package market;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Market{
  private LinkedList<Fruit> fruits;

  public Market(String filename) {
    fruits = new LinkedList<>();
    try {
      Scanner sc = new Scanner(new File(filename));
      while(sc.hasNextLine()){
        String line = sc.nextLine();
        String[] parts = line.split(",");
      }
    } catch (FileNotFoundException e) {
      //  : handle exception
    }
    System.out.print(fruits);
  }
}