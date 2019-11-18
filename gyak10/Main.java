class Main {
  public static void main(String[] args) {
    // A a = new A(6);
    // B b = new B(4, 6);
    // System.out.println(a.f() + " " + b.f(3.5));
    
    A a = new A(5);
    System.out.println(a.f(3.5));

    B b = new B(4, 7);
    System.out.println(b.f(3.5));
  }
}