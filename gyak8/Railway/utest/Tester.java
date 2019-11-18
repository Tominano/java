package utest;

import java.lang.reflect.Constructor;

public class Tester {
    public static TestResult run(String testable) {
        try {
            Class<?> cl = Class.forName(testable);
            Constructor<?> c = cl.getDeclaredConstructor();
            Testable t = (Testable) c.newInstance();
            System.out.println("Teszt: " + t.description());
            return t.run(true);
        }
        catch (Error|Exception e) {
            System.out.println("Teszt: " + testable);
            System.out.println("  ... hianyzik: " + e.getMessage());
        }
        return new TestResult(0, "Nem toltheto be valamelyik osztaly.", null);
    }

    public static void interpret(TestResult result) {
        if (result != null) {
            if (result.reason != null) {
                System.exit(1);
            }
            else
            if (result.apiError != null) {
                System.exit(2);
            }
        } else {
            System.out.println("Ismeretlen hiba tortent!");
            System.exit(3);
        }
    }
}
