package tests;

import market.Fruit;
import market.Market;
import java.io.*;
import utest.*;

import java.util.Arrays;

public class Part2 extends Testable {
    public void assertion() {
        try {
            Market m1 = new Market("badfilename.txt");
            check("Market konstruktora: hibas filenev eseten nem ures listaval inicilaizalta a piacot.", m1.numberOfFruits() == 0);
            Market m2 = new Market("fruits.txt");
            check("Market konstruktora: nem megfelelo mennyisegu adatot olvasott be.", m2.numberOfFruits() == 4);
            check("Market.show: rosszul jeleniti meg az objektumot, ha nincsenek gyumolcsok.", m1.show().equals(""));
            check("Market: vagy a konstruktor rogzit hibas adatokat, vagy a show metodus jeleniti meg oket rosszul.", m2.show().equals("korte (130 Ft)\nbarack (55 Ft)\ndinnye (2 015 Ft)\nszilva (130 Ft)"));
        } catch (Exception e) {
            check("A Market konstruktora nem engedheti ki a kivetelt a metodusbol", false);
        }
    }

    public String description() { return "2. resz"; }
    public String className() { return MarketApi.className; }

    public Object[] expectedMethods() throws Exception {
        return MarketApi.expectedMethods();
    }

    /*
    public Object[] expectedFields() throws Exception {
        return MarketApi.expectedFields();
    }
    */

    public static void main(String... args) {
        Test.main(new Part2());
    }
}
