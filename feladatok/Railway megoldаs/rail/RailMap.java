package rail;//(3)beírom a package-et

import java.util.LinkedList; 
import java.io.IOException;
//(3)Definiálom a classomat benne 2 rejtett adattaggal
public class RailMap {
    private Railway[] railways;
    private String country;
//(3)Ez a konstruktor, többit is kreálhatok
    public RailMap(String country, String[] lines) {
    //(3)Ez azért nem this. mert létre kell hozni egy új
        railways = new Railway[lines.length];
        int cnt = 0;
        this.country = country;
    //A Railway-ban meghívott make-et hívom meg hozzá
    //Hiszen ezért csináltam  
        for (int i=0; i<lines.length; ++i) {
            Railway r = Railway.make(lines[i]);
            if (r != null)
                railways[cnt++] = r;
        }
    }
//(4)
    public String toString() {
        return "RailMap(" + country + "," + railways + ")";
    }
//itt vannak a láncolt mappák
//(4)
    public String[] getNeighbours(String city) {
        String[] neighbours = new String[railways.length];
        int cnt = 0;
        for (int i=0; i<railways.length; ++i) {
            if (railways[i] == null) break;//kiugrik
            if (railways[i].hasEnd(city)) {
                String neighbour = railways[i].getOtherCity(city);
                neighbours[cnt++] = neighbour;
            }
        }
        return neighbours;
    }
// Összes város egy tömben, de mind csak egyszer
    public String[] getCities() {
        // *2 mert oda vissza kellenek a vonalak
        String[] cities = new String[railways.length*2];
        int cnt = 0;
        for (int i=0; i<railways.length; ++i) {
            if(railways[i] == null) break;//megáll
            String[] ends = railways[i].getCities();
            for (int j=0; j<2; ++j) {
                boolean contains = false;
                for(int k=0; k<cnt && !contains; ++k)
                    contains = cities[k].equals(ends[j]);
                if (!contains)
                    cities[cnt++] = ends[j];
            }
        }
        return cities;
    }
//(5)
    public String capitalCity() {
        String[] cities = getCities();
        int maxNeighbours = 0;
        String capital = "";
        for (int i=0; i<cities.length; ++i) {
            if(cities[i] == null)
                break;//kiugrik
            String[] neighbours = getNeighbours(cities[i]);
            int cnt;
            for(cnt = 0; neighbours[cnt] != null; ++cnt) {}
            if (cnt > maxNeighbours) {
                capital = cities[i];
                maxNeighbours = cnt;
            }
        }
        return capital;
    }
}
