/**
 * @author : Ionita Andra, grupa 2A7
 */
package HR;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.IntStream;

public class RandomProblem {
    public RandomProblem() {
    }
    
    public void GenerateAndSolveRandomProblem() {
 
        Faker faker = new Faker();
        Random rand = new Random();

        var fakeResident = IntStream.rangeClosed(0, 4).mapToObj(i -> new Resident(faker.name().fullName())).toArray(Resident[]::new);
        System.out.println("\n Generam rezidenti random:");
        for (var i : fakeResident) {
            System.out.println("\t" + i);
        }
        var fakeHospitals = IntStream.rangeClosed(0, 2).mapToObj(i -> new Hospital(faker.name().lastName() + " Hospital", rand.nextInt(5) + 1)).toArray(Hospital[]::new);

        System.out.println("Generam spitalele random");
        for (var i : fakeHospitals) {
            System.out.println("\t" + i + "  - generam capacitate random: " + i.getCapacity());
        }
        System.out.println("\n");


        List<Resident> fakeResidentList = new ArrayList<>();
        fakeResidentList.addAll(Arrays.asList(fakeResident));

        List<Hospital> fakeHospitalList = new ArrayList<Hospital>();
        fakeHospitalList.addAll(Arrays.asList(fakeHospitals));


        Map<Resident, List<Hospital>> fakeResPrefMap = new HashMap<>();

        for (var j : fakeResident) {
            List<Hospital> toAdd = new ArrayList<>();
            List<Hospital> filter = new ArrayList<>(fakeHospitalList.size());
            filter.addAll(fakeHospitalList);
            int maxPrefs = rand.nextInt(fakeHospitalList.size());
            for (int i = 0; i <= maxPrefs; i++) {
                int chosen = rand.nextInt(filter.size());
                toAdd.add(filter.get(chosen));
                filter.remove(chosen);
            }
            fakeResPrefMap.put(j, toAdd);
        }
        System.out.println("Preferintele random a rezidentilor");
        System.out.println(fakeResPrefMap);
        System.out.println("\n");

    
        Map<Hospital, List<Resident>> fakeHosPrefMap = new Hashtable<>();

        for (var j : fakeHospitals) {
            List<Resident> toAdd = new ArrayList<>();
            List<Resident> filter = new ArrayList<>(fakeResidentList.size());
            filter.addAll(fakeResidentList);
            int maxPrefs = rand.nextInt(fakeResidentList.size());
            for (int i = 0; i <= maxPrefs; i++) {
                int chosen = rand.nextInt(filter.size());
                toAdd.add(filter.get(chosen));
                filter.remove(chosen);
            }
            fakeHosPrefMap.put(j, toAdd);
        }
        System.out.println("Preferintele random a spitalelor:");
        System.out.println(fakeHosPrefMap);
        System.out.println("\n");

        Matching fake = new Matching();
        Map<Resident, Hospital> model = fake.CreateMatching(fakeResPrefMap);

        fake.IsStable(fakeResPrefMap, fakeHosPrefMap, fakeHospitalList, fakeResidentList, model);
    }
}
