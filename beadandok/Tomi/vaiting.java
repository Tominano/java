class vait {

    static void fut(int n) {
        
        for (int i = 0; i < n; ++i){
       	
           		System.out.print("#");
        		

			Thread.Sleep(10000); // 10000ms = 10s
	}

    }
    public static void main(String[] args){
    	fut(6);
    }
}
