class Rac {
	
	private int num, denom;
	
	public int getNum(){return num;}
	public void setNum(int num){this.num = num;}
	public int getDenom(){return denom;}
	public void setDenom(int denom){this.denom = denom;}


	int z; // adattag
	      //formális paraméterek
	int f(int x,int y) {
		//int x; <-- x már volt!
		int z; // lokális változó
		if(true) {
			z = 6;
			int p = 0;
		}
		for(int p=0;p<10;++p) {
			//int p;
			if(true) {
				//int p;
			}
		}
		//System.out.println(p);
		g(5,6/*aktuális paraméterek*/);
		return 1;
	}
	
	void g(int y, int z) {
		
	}
	// túlterhelés | overloading
	Rac() {
		num = 0;
		denom = 1;
	}
	
	Rac(int num, int denom) {
		this.num = num;
		this.denom = denom;
	}
	
	Rac(int val) {
		num = val;
		denom = 1;
	}
	

	Rac add(Rac a){
		return new Rac(a.num*denom + num*a.denom, a.denom*denom);//.simplify();
	}


	Rac mul(Rac a){
		return new Rac(num*a.num , denom * a.denom);//.simplify();
	}	

	Rac sub(Rac a){
		return new Rac(a.num*denom - num*a.denom, a.denom*denom);//.simplify();
	}

	Rac div(Rac a){
		return new Rac(num*a.denom,denom*a.num);//.simplify();
	}

	Rac simplify(){
		if (num%denom == 0){
				int x = num/denom;
		}	else if(denom%num == 0){
				int x = denom/num;
		}	else {int x = 1;}
		return this;
	}

//	Rac simplify() {
//		for(int x=2; x <= num && x <= denom; ++x) {
//			while(num % x == 0 && denom % x == 0) {
//				num /= x; // num = num / x;
//				denom /= x;
//			}
//		}
//		return this;
//	}


	//szignatúra | signature
	// hozzáférés + visszatérési típus + paraméterek típusa
	public String toString() {
		return "(" + num + "/" + denom + ")";
	}
}