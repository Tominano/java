package rail; // (1) beirom, hogy melyik package-be vagyok

public class Railway { //(1) Maga a class nen rejtett, 
    private String cityA, cityB; // csak az adattagok
    private int dist;
//(2)A final-al lesz osztályszintű konstans
    public static final Railway KESZTHELY_BUDAPEST = new Railway("Keszthely", "Budapest", 190);

    public Railway(String cityA, String cityB, int dist) {
        this.cityA = cityA;
        this.cityB = cityB; //(1) Ez a konstruktorunk
        this.dist = dist;
    }
//(2) OSZTÁLYSZINTŰ metódus"()"az van amit vár, de amit vár
//annak a nevét is meg kell adni, mint python ban.
//
    public static Railway make(String input) {
        String[] parts = input.split(" ");//(2)splitelem a parts-ba a szóközöknél
//(2)A "!" a logikai nemet jelenti        
        if (parts.length == 3 && !parts[0].isEmpty() && !parts[1].isEmpty() && !parts[2].isEmpty()) {
            for (int i=0; i<parts[2].length(); ++i)
        //(2)Ha a karakter egy szám a parts 2.indexében élvő "i" indexen        
                if(!Character.isDigit(parts[2].charAt(i)))
                    return null;
        //(2)A Railway 3. komponense egy számnak kell lennie méghozzá intnek
            return new Railway(parts[0], parts[1], Integer.parseInt(parts[2]));
        } else
            return null;
    }
//(4)
    public String toString() {
        return "Railway(" + cityA + " - " + cityB + " " + dist + ")";
    }

    public int getDistance() {
        return dist;//(1) A másik getter ami inst distel tér vissza
    }
//(2) Így néz ki ha OBJEKTUM szintű
//(2)Itt is elneveztem azt amit vár
//(2)Public után adom meg a visszatérés fajtáját
    public boolean hasEnd(String city) {
    //(2)ez vizsgálja hogy ha megeggyezik valamelyik adattaggal
        return cityA.equals(city) || cityB.equals(city);
    }

    public String[] getCities() {// (1) getter metódus ami stringet ad vissza
        return new String[] {cityA, cityB};
    }

    public String getOtherCity(String city) {
        if (cityA.equals(city))
            return cityB;
        if (cityB.equals(city))
            return cityA;
        return null;
    }
}
