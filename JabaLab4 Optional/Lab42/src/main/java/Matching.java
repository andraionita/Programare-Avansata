
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author Andra Ionita Grupa 2A7
 * In aceasta clasa am creat metoda de match prin care un rezident poate ajunge la spitalul dorit in ordinea preferintelor avand in vedere capacitatea maxima a spitalului.
 */

public class Matching {
    Map<Resident, Hospital> result = new HashMap<>();
    Problem pb;

    public Matching() {
        Map<Resident, Hospital> result = new HashMap<>();
    }

    public Map<Resident,Hospital> doMatch(Map<Resident, List<Hospital>> resPrefMap) {
        int i = 0;
        int cnt=0;
        //System.out.println(resPrefMap);
        for (Resident resident : resPrefMap.keySet()) {
            while (resPrefMap.get(resident).get(i).getCapacity() == 0)
                ++i;
            if (resPrefMap.get(resident).get(i).getCapacity() > 0) {
                cnt++;
                result.put(resident, resPrefMap.get(resident).get(i));
                System.out.println("Match " +cnt+" = [ Rezidentul: "+resident+", Spitalul: "+resPrefMap.get(resident).get(i)+" ]\n");
                //System.out.println("Spitalul: "+resPrefMap.get(resident).get(i));
                resPrefMap.get(resident).get(i).decreaseCapacity();
            }
        }
        System.out.println("\nUn match ar fi = " + result);
        return result;
    }
}
