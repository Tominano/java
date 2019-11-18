class Item {
	int id;
	private static int counter;
	
	public static int getCounter() {
		return counter;
		//return id;
	}
	
	Item() {
		id = counter++;
	}
}