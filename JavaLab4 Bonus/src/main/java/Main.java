/**
 * @author : Ionita Andra, grupa 2A7
 */

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Main {

   
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

       

        GaleShapleyAlgorithm GSModel = new GaleShapleyAlgorithm(GSResidentList, GSHospitalList, GSResPrefMap, GSHosPrefMap);

   
        GSModel.GetMatch();
    }
}
