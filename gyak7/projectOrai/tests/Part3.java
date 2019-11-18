package tests;

import utest.Tester;

public class Part3 extends Tester {

	public static int runAll() {
		int total = 0;
		total += run(Part3AuctionTest.class.getName()).score;
		return total;
	}
	
	public static void main(String... args) {
		int score = runAll();
		System.out.println("Part3 pontszam: " + score + " + ?");
		System.out.println("(A pluszpontokat az oktato meri fel, es az alappontokat is felulbiralhatja.)");
	}

}