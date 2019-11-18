package tests;

import market.Fruit;
import market.Market;
import java.io.*;
import java.util.*;
import utest.*;

import java.util.Arrays;

public class Part4 extends Testable {
    private boolean checkFruitLists(LinkedList<Fruit> list1, LinkedList<Fruit> list2) {
        if (list1.size() != list2.size()) return false;
        int i = 0;
        while (i < list1.size() && list1.get(i).show().equals(list2.get(i).show())) ++i;
        return (i >= list1.size());
    }
    public void assertion() {
        try {
            Market m = new Market("fruits.txt");
            check("Market.buyCheapestFruit: a metodus nem a megfelelo gyumolcsot adja vissza.", m.buyCheapestFruit().show().equals("barack (55 Ft)"));
            check("Market.buyCheapestFruit: a metodus nem csokkenti a listat.", m.numberOfFruits() == 3);
            check("Market.buyCheapestFruit: a metodus nem jo gyumolcsoket hagy benne a listaban.", m.show().equals("korte (130 Ft)\ndinnye (2 015 Ft)\nszilva (130 Ft)"));
            check("Market.buyCheapestFruit: a metodus nem a megfelelo gyumolcsot adja vissza, ha a legolcsobbal azonos aru is van.", m.buyCheapestFruit().show().equals("korte (130 Ft)"));
            check("Market.buyCheapestFruit: a metodus a megfelelo gyumolcsot tavolitja el, ha a legolcsobbal azonos aru is van.", m.show().equals("dinnye (2 015 Ft)\nszilva (130 Ft)"));
            m.buyCheapestFruit();
            check("Market.buyCheapestFruit: a metodus nem a megfelelo gyumolcsot adja vissza, ha mar csak egy gyumolcs volt.", m.buyCheapestFruit().show().equals("dinnye (2 015 Ft)"));
            check("Market.buyCheapestFruit: a metodus nem uriti ki a listat, ha elfogytak a gyumolcsok.", m.show().equals(""));
            check("Market.buyCheapestFruit: a metodus nem jol mukodik abban az esetben, ha nincsenek gyumolcsok.", m.buyCheapestFruit()==null);
            check("Market.buyCheapestFruit: a metodus nem jol mukodik abban az esetben, ha nincsenek gyumolcsok.", m.show().equals(""));
            m = new Market("fruits.txt");
            LinkedList<Fruit> expectedFruits = new LinkedList<Fruit>();
            expectedFruits.add(Fruit.make("barack", 55));
            expectedFruits.add(Fruit.make("korte", 130));
            expectedFruits.add(Fruit.make("szilva", 130));
            expectedFruits.add(Fruit.make("dinnye", 2015));
            check("Market.sale: a metodus nem megfelelo listat ad vissza.", checkFruitLists(m.sale(),expectedFruits));
            check("Market.sale: a metodus nem uriti ki a listat.", m.numberOfFruits() == 0);
            check("Market.sale: a metodus nem jol mukodik abban az esetben, ha nincsenek gyumolcsok.", m.sale().isEmpty());
        } catch (Exception e) {
            check("A Market konstruktora nem engedheti ki a kivetelt a metodusbol", false);
        }
    }

    public String description() { return "4. resz"; }
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
        Test.main(new Part4());
    }
}
