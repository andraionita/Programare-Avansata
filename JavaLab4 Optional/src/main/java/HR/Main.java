/**
 * @author : Ionita Andra, grupa 2A7
 */
package HR;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Main {
	////////////////////////////////COMPULSORY
    public static void main(String[] args) {
        var r = IntStream.rangeClosed(0, 3).mapToObj(i -> new Resident("R" + i)).toArray(Resident[]::new);
        var h = IntStream.rangeClosed(0, 3).mapToObj(i -> new Hospital("H" + i)).toArray(Hospital[]::new);
        h[0].setCapacity(1);
        h[1].setCapacity(2);
        h[2].setCapacity(2);

        List<Resident> residentList = new ArrayList<>();
 

        residentList.addAll(Arrays.asList(r));

        Set<Hospital> hospitalSet = new TreeSet<Hospital>();

        hospitalSet.addAll(Arrays.asList(h));

        List<Resident> newSortedList = residentList.stream().sorted(Comparator.comparing(Resident::getName)).collect(Collectors.toList());

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        Map<Hospital, List<Resident>> hosPrefMap = new Hashtable<>();

        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));

        hosPrefMap.put(h[0], Arrays.asList(r[0], r[1], r[2], r[3]));
        hosPrefMap.put(h[1], Arrays.asList(r[0], r[1], r[2]));
        hosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[3]));

        System.out.println(resPrefMap);
        System.out.println(hosPrefMap);

        System.out.println("Rezidentii care au acceptat H0 si H2");
        List<Hospital> target = Arrays.asList(h[0], h[2]);
        List<Resident> result = residentList.stream().filter(res -> resPrefMap.get(res).containsAll(target)).collect(Collectors.toList());
        System.out.println(result);
        System.out.println("Rezidentii care au R0 ca pe rezident favorit");
        try {
            hospitalSet.stream().filter(hos -> hosPrefMap.get(hos).get(0).equals(r[0])).forEach(System.out::println);
        } catch (Exception e) {
            
        }
 
    
////////////////////////////OPTIONAL
        Matching matching = new Matching();
        matching.CreateMatching(resPrefMap);

        RandomProblem p = new RandomProblem();
        p.GenerateAndSolveRandomProblem();

        ////////////////////// BONUS
        var GSRes = IntStream.rangeClosed(0, 2).mapToObj(i -> new Resident("R" + i)).toArray(Resident[]::new);
        var GSHos = IntStream.rangeClosed(0, 2).mapToObj(i -> new Hospital("H" + i, 1)).toArray(Hospital[]::new);
        List<Resident> GSResidentList = new ArrayList<>();
        GSResidentList.addAll(Arrays.asList(GSRes));
        List<Hospital> GSHospitalList = new ArrayList<>();
        GSHospitalList.addAll(Arrays.asList(GSHos));
        Map<Resident, List<Hospital>> GSResPrefMap = new HashMap<>();
        Map<Hospital, List<Resident>> GSHosPrefMap = new HashMap<>();
        GSResPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        GSResPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        GSResPrefMap.put(r[2], Arrays.asList(h[0], h[1], h[2]));
        GSHosPrefMap.put(h[0], Arrays.asList(r[1], r[0], r[2]));
        GSHosPrefMap.put(h[1], Arrays.asList(r[1], r[2], r[0]));
        GSHosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[2]));

       

        GaleShapley GSModel = new GaleShapley(GSResidentList, GSHospitalList, GSResPrefMap, GSHosPrefMap);

   
        GSModel.GetMatch();
    }
}
