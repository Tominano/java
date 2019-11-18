package tests;

import static utest.Testable.method;
import static utest.Testable.staticMethod;
import static utest.Testable.constructor;
import static utest.Testable.staticField;

public class FruitApi {
    public static String className = "market.Fruit";

    public static Object[] expectedMethods() throws Exception {
        return new Object[]
            { constructor(className, new Class[] {String.class, Integer.TYPE})
            , staticMethod(className + ".make", String.class, Integer.TYPE)
            , method(className + ".getPrice")
            , method(className + ".cheaperThan", market.Fruit.class)
            , method(className + ".show")
            , staticMethod(className + ".getCheapestFruit")
            };
    }
}
