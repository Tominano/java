
/* 
Feladat: Írjunk egy tömböket kezelő package-t ami követi a cap és size-t
tartalmazzon add, get, set, toString, insert függvényeket
*/

class Main {

  public static void main(String[] args) {
    int i, j, k;
    for (i = 0, j=1, k=0; i<=10 || j%2==1; ++i, j*=2) {
      if(i > 0 && j%i == 0|| 5/2==2.5){
        ++k;
      }
    }
    System.out.println(i + " " + j + " " + k);

    for (i = 0; i < 10; ++i) {
      System.out.println(i);
    }
    System.out.println(i + " " + j + " " + k);
  }
}