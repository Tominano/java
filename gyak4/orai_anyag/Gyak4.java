import java.util.Arrays;

class Gyak4 {
	
	private static void sort(int[] t) {
		// TODO: rendezés
		Arrays.sort(t);
	}
	
	
	public static void main(String[] args) {
		System.out.println("------------");
		for(int i=0;i<args.length;++i)
			System.out.println(args[i]);
		System.out.println("------------");
		
		int sum = 0;
		for(String arg : args) {
			sum += Integer.parseInt(arg);
		}
		System.out.println("sum = " + sum);
		
		
		int[] intArgs = new int[args.length];
		for(int i=0;i<args.length;++i)
			intArgs[i] = Integer.parseInt(args[i]);
		sort(intArgs);
		for(int i : intArgs)
			System.out.print(i + " ");
		System.out.println();
		
		
		Item i0 = new Item();
		Item i1 = new Item();
		Item i2 = new Item();
		
		System.out.println(i2.id);
		System.out.println(Item.getCounter());
		//System.out.println(i2.getCounter());
		
		Item[] t = new Item[101];
		System.out.println(t.length);
		for(int i=0;i<=100;++i) {
			t[i] = new Item();
			//System.out.println(t[i].id);
		}
		
	}
}