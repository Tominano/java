package tests;

import market.Fruit;
import utest.*;

import java.util.Arrays;

public class Part1 extends Testable {
    public void assertion() {
        check("Fruit.getCheapestFruit: a metodusnak null-t kell visszaadnia, ha meg nem hoztak letre Fruit objektumot.", Fruit.getCheapestFruit() == null);
        Fruit f1 = Fruit.make("A", 15);
        check("Fruit.make: tul rovid nevhez is letrehozza az objektumot.", f1 == null);
        f1 = Fruit.make("ABC", -5);
        check("Fruit.make: negativ arnal is letrehozza az objektumot.", f1 == null);
        f1 = Fruit.make("ABC", 0);
        check("Fruit.make: nulla arnal is letrehozza az objektumot.", f1 == null);
        f1 = Fruit.make("ABC", 5005);
        check("Fruit.make: tul nagy arnal is letrehozza az objektumot.", f1 == null);
        f1 = Fruit.make("ABC", 16);
        check("Fruit.make: nem 0-ra vagy 5-re vegzodo arnal is letrehozza az objektumot.", f1 == null);
        f1 = Fruit.make("AB C", 200);
        check("Fruit.make: illegalis karaktert tartalmazo nevnel is letrehozza az objektumot.", f1 == null);
        f1 = Fruit.make("alma", 700);
        check("Fruit.make: helyes parameterekkel sem hozza letre az objektumot.", f1 != null);
        check("Fruit.getPrice: a metodus nem ad vissza helyes adatot.", f1.getPrice() == 700);
        Fruit f2 = Fruit.make("korte", 600);
        check("Fruit.cheaperThan: a metodus nem ad vissza helyes adatot.", !f1.cheaperThan(f2));
        check("Fruit.cheaperThan: a metodus nem ad vissza helyes adatot.", f2.cheaperThan(f1));
        check("Fruit.show: a metodus nem ad vissza helyes adatot 1000-nel kisebb arnal.", f1.show().equals("alma (700 Ft)"));
        Fruit f3 = Fruit.make("barack", 1125);
        check("Fruit.show: a metodus nem ad vissza helyes adatot 1000-nel nagyobb arnal.", f3.show().equals("barack (1 125 Ft)"));
        Fruit f4 = Fruit.make("gorogdinnye", 1025);
        check("Fruit.show: a metodus nem ad vissza helyes adatot, ha az ezres tagolas utan egy 0 jon.", f4.show().equals("gorogdinnye (1 025 Ft)"));
        Fruit f5 = Fruit.make("eper", 1005);
        check("Fruit.show: a metodus nem ad vissza helyes adatot, haaz ezres tagolas utan ket 0 jon.", f5.show().equals("eper (1 005 Ft)"));
        Fruit f6 = Fruit.make("sargadinnye", 1000);
        check("Fruit.show: a metodus nem ad vissza helyes adatot, ha az ar 1000-el oszthato.", f6.show().equals("sargadinnye (1 000 Ft)"));
        check("Fruit.getCheapestFruit: a metodus nem a valaha letrehozott legolcsobb Fruit objektumot adja vissza.", Fruit.getCheapestFruit().show().equals(f2.show()));
        Fruit f7 = Fruit.make("szilva", 600);
        check("Fruit.cheaperThan: a metodus nem ad vissza helyes adatot egyforma aru gyumolcsoknel.", !f7.cheaperThan(f2));
        check("Fruit.cheaperThan: a metodus nem ad vissza helyes adatot egyforma aru gyumolcsoknel.", !f2.cheaperThan(f7));
        check("Fruit.cheaperThan: a metodus nem ad vissza helyes adatot egyforma aru gyumolcsoknel.", !f2.cheaperThan(f7));
        check("Fruit.getCheapestFruit: ha a valaha letrehozott legolcsobb gyumolccsel egyforma aru is van, akkor a metodusnak ezek kozul a legkorabban letrehozottat kell visszaania.", Fruit.getCheapestFruit().show().equals(f2.show()));
    }

    public String description() { return "1. resz"; }
    public String className() { return FruitApi.className; }

    public Object[] expectedMethods() throws Exception {
        return FruitApi.expectedMethods();
    }

    /*
    public Object[] expectedFields() throws Exception {
        return FruitApi.expectedFields();
    }
    */

    public static void main(String... args) {
        Test.main(new Part1());
    }
}
