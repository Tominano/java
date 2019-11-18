import utest.Tester;

public class TestRunner extends Tester {
	public static void runAll() {
		int total = 0;
		total += run("tests.Part1LotTest").score;
		total += run("tests.Part2AuctionTest").score;
		total += run("tests.Part3AuctionTest").score;
		System.out.println("Osszesitett pontszam: " + total + " + ?");
		System.out.println("(A pluszpontokat az oktato meri fel, es az alappontokat is felulbiralhatja.)");
	}

}