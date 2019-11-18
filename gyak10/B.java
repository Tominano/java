class B extends A {
  int y;
   B(int x, int y) {
     super(x); // <-- ez az ős osztály konstruktora és az x-et atdjuk
     this.y = y;
   }

   @Override int f(double d){
    return (int)d*(x+y);
   }
}