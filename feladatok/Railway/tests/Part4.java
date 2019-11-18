package tests;

import rail.Railway;
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

public class Part4 extends Testable {
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
        String[] budapestNeighbours = new String[] {"Salakszentmotoros", "Bubanatvolgy", "Keszthely"};

        String[] actualCities = r.getNeighbours(new String("Budapest"));

        check("RailMap.getNeighbours(): null az eredmeny.", actualCities != null);

        check("RailMap.getNeighbours(): nem adja vissza Budapest szomszedait.", myEquals(budapestNeighbours,actualCities));

        String[] keszthelyNeighbours = new String[] { "Budapest" };
            
        actualCities = r.getNeighbours(new String("Keszthely"));
        check("RailMap.getNeighbours(): nem adja vissza Keszthely szomszedait.", myEquals(keszthelyNeighbours,actualCities));

        check("RailMap.getNeighbours(): Miskolcnak nem szabadna, hogy szomszedai legyenek.", r.getNeighbours(new String("Miskolc"))[0] == null);

        Railway rw = new Railway("Szigliget", "Tapolca", 20);

        check("Railway.toString(): nem kezdodik \"Railway(\"-jel.", rw.toString().startsWith("Railway("));
        check("Railway.toString(): nem tartalmazza az egyik varost.", rw.toString().contains("Szigliget"));
        check("Railway.toString(): nem tartalmazza a masik varost.", rw.toString().contains("Tapolca"));
        check("Railway.toString(): nem tartalmazza a tavolsagot.", rw.toString().contains("20"));

        check("RailMap.toString: nem kezdodik \"RailMap(\"-pal.", r.toString().startsWith("RailMap("));
    }

    public String description() { return "4. resz"; }
    public String className() { return "rail.RailMap"; }

    public Object[] expectedMethods() throws Exception {
        return new Object[]
            { constructor(className(), new Class[] {String.class, String[].class})
            , method(className() + ".toString")
            , method(className() + ".getNeighbours", String.class)
            , optionalMethod(className() + ".getCities")
            , optionalMethod(className() + ".capitalCity")
            };
    }

    public Object[] expectedFields() throws Exception {
        return new Object[] {};
    }

    public static void main(String... args) {
        Test.main(new Part4());
    }
}
