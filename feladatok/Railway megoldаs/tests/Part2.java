package tests;

import rail.Railway;
import utest.*;

import java.util.Arrays;

public class Part2 extends Testable {
    public void assertion() {
        check("Railway.make(): ures stringbol nem johet letre objektum.", Railway.make(new String()) == null);
        check("Railway.make(): egy varosbol nem johet letre objektum.", Railway.make("Esztergom") == null);
        check("Railway.make(): egy varosbol es tavolsagbol nem johet letre objektum.", Railway.make("Esztergom 60") == null);
        check("Railway.make(): ket varosbol nem johet letre objektum.", Railway.make("Esztergom Salakszentmotoros") == null);
        check("Railway.make(): harom varosbol nem johet letre objektum.", Railway.make("Esztergom Salakszentmotoros Felsozsebalso") == null);
        Railway r = Railway.make("Salakszentmotoros Felsozsebalso 50");
        check("Railway.make(): ket varosbol egy tavolsagbol letre kell jonnie objektumnak.", r != null);

        check("Railway.hasEnd(): nem ad vissza igazat az egyik varosra.", r.hasEnd(new String("Salakszentmotoros")));
        check("Railway.hasEnd(): nem ad vissza igazat a masik varosra.", r.hasEnd(new String("Felsozsebalso")));
        check("Railway.hasEnd(): igazat ad egy teljesen masik varosra.", !r.hasEnd(new String("Budapest")));

        check("Railway.getOtherCity(): nem adja vissza az egyik szomszedos varost.", "Salakszentmotoros".equals(r.getOtherCity(new String("Felsozsebalso"))));
        check("Railway.getOtherCity(): nem adja vissza a masik szomszedos varost.", "Felsozsebalso".equals(r.getOtherCity(new String("Salakszentmotoros"))));
        check("Railway.getOtherCity(): nem ad nullt, ha a vasutvonal nem erinti a parameter varost.", r.getOtherCity("Budapest") == null);

        String[] keszthelyBudapest = Railway.KESZTHELY_BUDAPEST.getCities();
        Arrays.sort(keszthelyBudapest);
        check("KESZTHELY_BUDAPEST: A ket varos nem Keszthely es Budapest.", Arrays.equals(new String[] {"Budapest", "Keszthely"}, keszthelyBudapest));
        check("KESZTHELY_BUDAPEST: A tavolsag nem 190.", Railway.KESZTHELY_BUDAPEST.getDistance() == 190);
    }

    public String description() { return "2. resz"; }
    public String className() { return "rail.Railway"; }

    public Object[] expectedMethods() throws Exception {
        return new Object[]
            { optionalConstructor(className(), new Class[] {String.class, String.class, Integer.TYPE})
            , optionalConstructor(className())
            , staticMethod(className() + ".make", String.class)
            , optionalMethod(className() + ".toString")
            , optionalMethod(className() + ".getCities")
            , optionalMethod(className() + ".getDistance")
            , method(className() + ".hasEnd", String.class)
            , method(className() + ".getOtherCity", String.class)
            };
    }

    public Object[] expectedFields() throws Exception {
        return new Object[] 
            { staticField(className() + ".KESZTHELY_BUDAPEST")
            };
    }

    public static void main(String... args) {
        Test.main(new Part2());
    }
}
