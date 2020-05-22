/**
 * @author : Ionita Andra, grupa 2A7
 */
package HR;

import java.util.*;

public class Matching {
    Map<Resident, Hospital> match = new HashMap<>();

    public Matching() {
    }

 

    public Map<Resident, Hospital> CreateMatching(Map<Resident, List<Hospital>> resPrefMap) throws ArrayIndexOutOfBoundsException {
        int i = 0;
        try {
            for (Resident resident : resPrefMap.keySet()) {
                while (resPrefMap.get(resident).get(i).getCapacity() == 0)
                    ++i;
                if (resPrefMap.get(resident).get(i).getCapacity() > 0) {
                    match.put(resident, resPrefMap.get(resident).get(i));
                    resPrefMap.get(resident).get(i).decreaseCapacity();
                }
            }
            System.out.println("Un match este " + match);
        } catch (Exception e) {
           
        }
        return match;
    }


    public boolean IsStable(Map<Resident, List<Hospital>> resPrefMap, Map<Hospital, List<Resident>> hosPrefMap, List<Hospital> hospitalSet, List<Resident> residentList, Map<Resident, Hospital> matching) {
        if (matching.size() == resPrefMap.size()) {
            for (Resident resident : matching.keySet()) {
                

                int indexOfMatchingHospitalInResPref = resPrefMap.get(resident).indexOf(matching.get(resident));

                Hospital betterPreferredHospital = new Hospital();
                if (indexOfMatchingHospitalInResPref > 0)
                    for (int i = 1; i <= indexOfMatchingHospitalInResPref; i++) {
                        betterPreferredHospital = resPrefMap.get(resident).get(indexOfMatchingHospitalInResPref - i);
                        int indexOfResidentInHospitalList = hosPrefMap.get(betterPreferredHospital).indexOf(resident);
                        if (indexOfResidentInHospitalList != hosPrefMap.get(betterPreferredHospital).size() - 1)
                            for (int j = hosPrefMap.get(betterPreferredHospital).size() - 1; j > indexOfResidentInHospitalList; --j)
                                if (matching.get(hosPrefMap.get(betterPreferredHospital).get(j)).equals(betterPreferredHospital)) {
                                    System.out.println("Match nu e stabil intre " + resident + " si " + matching.get(resident));
                                    return false;
                                }
                    }
            }
            System.out.println("Match stabil");
        } else System.out.println("Nu exista match.");
        return true;
    }
}
