import java.io.IOException;

import array.Array;

public class Main {
	
	public static void main(String[] args) throws IOException{
		Array a = new Array(10);
		System.out.println(a);
		for(int i=0;i<10;++i)
			a.add(i);
		System.out.println(a);
		a.add(99);
		System.out.println(a);
		a.set(0,33);
		System.out.println(a);
		System.out.println(a.get(10));
		System.out.println(a.get(11));
	}
}