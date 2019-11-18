package tests;

import static utest.Testable.method;
import static utest.Testable.staticMethod;
import static utest.Testable.constructor;
import static utest.Testable.staticField;

public class MarketApi {
    public static String className = "market.Market";

    public static Object[] expectedMethods() throws Exception {
        return new Object[]
            { constructor(className, new Class[] {String.class})
            , method(className + ".numberOfFruits")
            , method(className + ".show")
            , method(className + ".cheaperThan", market.Fruit.class)
            , method(className + ".average")
            , method(className + ".buyCheapestFruit")
            , method(className + ".sale") 
            };
    }
}
