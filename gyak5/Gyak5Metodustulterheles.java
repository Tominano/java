class Gyak5Metodustulterheles {
  int f(int a, int b) {
    return a + b;
  }

  int f(int a, boolean b) {
    return a;
  }

  int f(int a) {
    return f(a, 1);
  }

  int f(int b) {
    return b;
  }
}

// Ezt hívjuk metodus túlterhelésnek amiknek a signaturája különböző