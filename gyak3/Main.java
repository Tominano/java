

class Main {
	
	public static void main(String[] args) {
		Rac r = new Rac(3, 10);
		System.out.println(r);
		Rac r2 = new Rac(2);
		//r2.num = 5;
		System.out.println(r.add(r2)); // egyikfele.add(másikfele)
		System.out.println(r.mul(r2));
		System.out.println(r.sub(r2));
		System.out.println(r.div(r2));
		r = r2;
		//r.num = 6;
		System.out.println(r2);
	}
}