class Main {
   
    public static void main(string[] args){
        Rac r;
        r = new Rac();
        r.num = 3;
        r.denom = 10;
        System.out.println(rnum + " " + r.denom);
//komment
        Rac r2 = new Rac();
        r2.num = 5;
        System.out.println(r2.num);
        
        r = r2;
        r.num = 6;
        System.out.println(r2.num);
    }
}