// Tombok

class Gyak4Argumentumok {

  private static void Sort(int[] t) {
    ToDo functionmegiras
  }

  public static void main(String[] args) {

    for (int i = 0; i < args.length; i++) {
      System.out.println(args[i]);
    }

    int sum = 0;
    for (String arg : args) { // <-- enchanced for a tomb elemeit pakolja be az arg-ba
      sum += Integer.parseInt(arg);
    }
    System.out.println("sum = " + sum);

    int[] intArgs = new int[args.length];
    for (int i=0; i<args.length; ++i)
      intArgs[i] = Integer.parseInt(args[i])

    sort{intArgs};
  }

}