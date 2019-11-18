/*
Készítsd el a MyMath osztályt, melynek egyetlen fibo nevű metódusa:

osztály szintű
egy egész szám tömböt állít elő
paramétere egy n egész szám
előállítja a fibonacci sorozat (1,1,2,3,5,8,...) első n tagját
Az osztályt nem árt tesztelni (egy másik osztály main metódusában példányosítva), de feltölteni csak a MyMath-t kell.

Figyelj a kódolási konvenciókra!
*/

public class MyMath {
	
	private int[] X;
	private int n;
	
	public MyMath(int n) {
		X = new int[n];
		this.n = n;
	}
	
	public int[] fibo(int n) {
    X[0] = 1;
    X[1] = 1;

    if(n<2) 
      throw new IllegalArgumentException("invalid n");

    for (int i = 2; i < n; i++) {
      X[i] = X[i-1] + X[i-2];
    }
		return X;
	}
	
}