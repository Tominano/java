class MyMath {
	private int x;
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	
	MyMath(int x) { setX(x); }
	
	boolean isPrime() {
		for(int i = 2; i*i <= x; ++i) {
			if(x % i == 0) {
				return false;
			}
		}
		return true;
	}
}