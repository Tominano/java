package utest;

public class Test {
    public static void main(Testable t) {
        TestResult result = null;

        result = t.run(false);

        if (result != null) {
            if (result.reason == null && result.apiError == null) {
                System.out.println(":-)");
                System.exit(0);
            } else
            if (result.reason != null) {
                System.out.println(String.format(":-( [Hibas: %s]\n", result.reason));
                System.out.println("A hiba megertesehez lasd a megfelelo teszt forraskodjaban az adott cimkevel\nrendelkezo check() hivast.");
                System.exit(1);
            }
            else
            if (result.apiError != null) {
                System.out.println(":-(\n");
                System.out.println(result.apiError);
                System.exit(2);
            }
        } else {
            System.out.println(":-( Ismeretlen hiba tortent!");
            System.exit(3);
        }
    }
}
