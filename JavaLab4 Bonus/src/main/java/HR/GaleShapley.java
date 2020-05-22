/**
 * @author : Ionita Andra, grupa 2A7
 * Aici am implementat algoritmul Gale Shapley pentru a rezolva problema de HR.
*  
 */
package HR;

import java.util.*;


public class GaleShapley {
    private List<Resident> residentList;
    private List<Hospital> hospitalList;
    private Map<Resident, List<Hospital>> resPrefMap;
    private Map<Hospital, List<Resident>> hosPrefMap;

    public GaleShapley(List<Resident> residentList, List<Hospital> hospitalList, Map<Resident, List<Hospital>> resPrefMap, Map<Hospital, List<Resident>> hosPrefMap) {
        this.residentList = residentList;
        this.hospitalList = hospitalList;
        this.resPrefMap = resPrefMap;
        this.hosPrefMap = hosPrefMap;
    }

    public void GetMatch() {

		//adaugam spitalele intr-un HashSet
        Set<Resident> availableResidents = new HashSet<Resident>(residentList);

        Map<Hospital, List<Resident>> availableHospitals = new HashMap<Hospital, List<Resident>>();
        for (Hospital h : hospitalList)
            availableHospitals.put(h, null); //null inseamna ca niciun rezident nu a fost alocat
        int size = availableHospitals.size();
        Resident currentResident = new Resident();

		//loop pana cand gasim un rezident neasignat
        while (size > 0) { //facem asta pana cand toti rezidenti sunt asignati la un spital
            currentResident = availableResidents.iterator().next();
            System.out.println("\nResident " + currentResident + " a intrat in spital:");
            ArrayList<Resident> temp = new ArrayList<Resident>();
            ArrayList<Resident> tempNew = new ArrayList<Resident>();

            for (Hospital h : resPrefMap.get(currentResident)) { //loop printre preferintele rezidentului
    
                ArrayList<Resident> matchedResidents = new ArrayList<Resident>();
                matchedResidents.addAll(availableHospitals.get(h)); 

                if (matchedResidents.size() < h.getCapacity())
                {
                    temp.add(currentResident);
                    availableHospitals.put(h, temp); 
                    h.decreaseCapacity();
                    availableResidents.remove(currentResident); //marcam rezidentii asignati
                    System.out.println("Rezidentul " + currentResident + " a fos asignat la spitalul " + h);
                    break;
                } else 
                {
                    int prefForMatchedResident = hosPrefMap.get(matchedResidents).indexOf(currentResident);
                    int prefForLastResident = hosPrefMap.get(matchedResidents).size() - 1;
                    if (prefForLastResident > prefForMatchedResident) //daca rezidentul care vine are o pozitie mai buna
                    {
                        try {
                            tempNew.remove(tempNew.get(tempNew.size() - 1)); 
                        } catch (Exception e) {
                        }
                        tempNew.add(currentResident);
                        availableHospitals.put(h, tempNew); 
                        availableResidents.remove(currentResident); 
                        availableResidents.addAll(matchedResidents);
                        System.out.println("Rezidentul " + matchedResidents + "a fost dat afara din spitalul " + h);
                        System.out.println("Rezidentul " + currentResident + " a fost asignat la spitalul " + h);
                        break;
                    }
                }
            }
            size = availableResidents.size();
        }

        Iterator<Map.Entry<Hospital, List<Resident>>> itr = availableHospitals.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<Hospital, List<Resident>> entry = itr.next();
            System.out.println("Rezidentul/Rezidentii " + entry.getValue() + " asignat/i la spitalul " + entry.getKey());
        }
    }
}
