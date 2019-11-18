class Beadando {

    static void oszto(int n, int m, int k) {
        
        for (int i = 0; i < n; ++i){
       		for (int j = 0; j < m; ++j){
           		if ((i+j) % k == 0){
                		System.out.print("#");
            }
           		else {
                		System.out.print(" ");
            	}
		
	    }
        System.out.println(" "); }	
    }


    public static void main(String[] args){
    	oszto(6,8,5);
    }
}
