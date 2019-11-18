package tests;

import rail.Railway;
import utest.*;

import java.util.Arrays;

public class Part1 extends Testable {
    public void assertion() {
        final int dist = 50;
        Railway r = new Railway("Salakszentmotoros", "Felsozsebalso", dist);
        String[] cities = r.getCities();
        Arrays.sort(cities);
        check("Railway.getCities(): nem tartalmazza a helyes varosokat.", Arrays.equals(cities, new String[]{"Felsozsebalso", "Salakszentmotoros"}));
        check("Railway.getDistance(): nem adja vissza a helyes tavolsagot.", r.getDistance() == dist);
    }

    public String description() { return "1. resz"; }
    public String className() { return "rail.Railway"; }

    public Object[] expectedMethods() throws Exception {
        return new Object[]
            { constructor(className(), new Class[] {String.class, String.class, Integer.TYPE})
            , optionalStaticMethod(className() + ".make", String.class)
            , optionalMethod(className() + ".toString")
            , method(className() + ".getCities")
            , method(className() + ".getDistance")
            , optionalMethod(className() + ".hasEnd", String.class)
            , optionalMethod(className() + ".getOtherCity", String.class)
            };
    }

    public Object[] expectedFields() throws Exception {
        return new Object[] 
            { optionalStaticField(className() + ".KESZTHELY_BUDAPEST")
            };
    }

    public static void main(String... args) {
        Test.main(new Part1());
    }
}
