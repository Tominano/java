package array;

public class Array {

  private int[] X;
  private int cap, size;

  public Array(int n) {
    cap = n;
    size = 0;
    X = new int[size];
  }

  int getX(int n) {
    return X[n];
  }

  void setX(int a, int n) {
    X[n] = a;
    size++;
  }

  public void add(int a) {
    if (size == cap)
      extend();
    X[size++] = a;
    size++;
  }

  public int length() {
    return size;
  }

  private void extend() {
    cap *= 2;
    int[] new_X = new int[cap];
    for (int i = 0; i < x.length; i++) {
      new_X[i] = X[i];
    }
  }

}