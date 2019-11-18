package tests;

import rail.RailMap;
import utest.*;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Part3 extends Testable {

    private boolean myEquals(String[] a, String[] b) {
        ArrayList<String> p = new ArrayList<>(Arrays.asList(a));
        p.removeAll(Collections.singleton(null));
        ArrayList<String> q = new ArrayList<>(Arrays.asList(b));
        q.removeAll(Collections.singleton(null));
        Collections.sort(p);
        Collections.sort(q);
        return p.equals(q);
    }
    
    public void assertion() throws IOException {
        Scanner in = new Scanner(new File("railmap.txt"));
        LinkedList<String> lst = new LinkedList<>();
        while(in.hasNext()) {
            lst.add(in.nextLine());
        }
        RailMap r = new RailMap("Magyarorszag", lst.toArray(new String[lst.size()]));
        String[] expectedCities = new String[] {"Szigliget", "Salakszentmotoros", "Bubanatvolgy", "Budapest", "Siofok", "Keszthely"};

        String[] actualCities = r.getCities();

        check("RailMap.getCities(): null az eredmeny.", actualCities != null);

        check("RailMap.getCities(): nem adja vissza az osszes varost.", myEquals(expectedCities,actualCities));
    }

    public String description() { return "3. resz"; }
    public String className() { return "rail.RailMap"; }

    public Object[] expectedMethods() throws Exception {
        return new Object[]
            { constructor(className(), new Class[] {String.class, String[].class})
            , optionalMethod(className() + ".toString")
            , optionalMethod(className() + ".getNeighbours", String.class)
            , method(className() + ".getCities")
            , optionalMethod(className() + ".capitalCity")
            };
    }

    public Object[] expectedFields() throws Exception {
        return new Object[] {};
    }

    public static void main(String... args) {
        Test.main(new Part3());
    }
}
