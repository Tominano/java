package tests;

import utest.Tester;

public class Part2 extends Tester {

	public static int runAll() {
		int total = 0;
		total += run(Part2AuctionTest.class.getName()).score;
		return total;
	}
	
	public static void main(String... args) {
		int score = runAll();
		System.out.println("Part2 pontszam: " + score + " + ?");
		System.out.println("(A pluszpontokat az oktato meri fel, es az alappontokat is felulbiralhatja.)");
	}

}