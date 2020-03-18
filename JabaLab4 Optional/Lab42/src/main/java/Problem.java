
import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.IntStream;


/**
 * @author Andra Ionita Grupa 2A7
 * In aceasta clasa am creat o instanta rondom pentru problema HR.
 * Am folosit libraria Faker pentru nume si functii stream.
 */

public class Problem {

    public static void ResProblem(){
        /////////////////////////////////////////////fake/////////////////////////////////////////////////////////////////////////
        System.out.println("\nGenerator pentru problema cu date fake\n");
        Faker faker = new Faker();
        Random rand = new Random();

        System.out.println("Numele rezidentilor sunt:\n");
        var fakeR = IntStream.rangeClosed(0, 7).mapToObj(i -> new Resident(faker.name().fullName())).toArray(Resident[]::new);
        for(var i : fakeR)
        {
            System.out.println(i);
        }
        System.out.println("\nNumele Spitalelor si capacitatile lor sunt:");
        var fakeH = IntStream.rangeClosed(0, 8).mapToObj(i -> new Hospital(faker.company().name(), rand.nextInt(5) + 1)).toArray(Hospital[]::new);

        System.out.println("\n");
        for(var i : fakeH)
        {
            System.out.println(i+"  -  "+i.getCapacity());
        }
        System.out.println("\n");


        List<Resident> fakeResidentList = new ArrayList<>();
        fakeResidentList.addAll(Arrays.asList(fakeR));

        List<Hospital> fakeHospitals = new ArrayList<Hospital>();
        fakeHospitals.addAll(Arrays.asList(fakeH));

        //////////////////////////generam random lista de preferinte pt fiecare rezident
        Map<Resident, List<Hospital>> fakeResPrefMap = new HashMap<>();

        for(var j : fakeR) {

            List<Hospital> temporar=new ArrayList<>();
            List<Hospital> temporarDelete=new ArrayList<>(fakeHospitals);


            int numberOfElements = rand.nextInt(fakeHospitals.size());

            for (int i = 0; i <=numberOfElements; i++) {
                temporar.add(temporarDelete.get(rand.nextInt(temporarDelete.size())));
                temporarDelete.remove(rand.nextInt(temporarDelete.size()));
            }
            fakeResPrefMap.put(j, temporar);

        }
        System.out.println("Lista de preferinte pentru fiecare rezident este:\n");
        System.out.println(fakeResPrefMap);
        System.out.println("\n");

///////////////////////geneream random lista de preferinte pentru fiecare spital
        Map<Hospital, List<Resident>> fakeHosPrefMap = new Hashtable<>();

        for(var j : fakeH) {

            List<Resident> temporar=new ArrayList<>();
            List<Resident> temporarDelete=new ArrayList<>(fakeResidentList);


            int numberOfElements = rand.nextInt(fakeResidentList.size());

            for (int i = 0; i <=numberOfElements; i++) {
                temporar.add(temporarDelete.get(rand.nextInt(temporarDelete.size())));
                temporarDelete.remove(rand.nextInt(temporarDelete.size()));
            }
            fakeHosPrefMap.put(j, temporar);

        }
        System.out.println("Lista de preferinte pentru fiecare spital este:\n");
        System.out.println(fakeHosPrefMap);
        System.out.println("\n");

        ///////////////////////////////////////////////////////facem algoritmul de matching
        Matching fake=new Matching();
        System.out.println("O rezolvare pentru problema HR poate fi:\n");
        fake.doMatch(fakeResPrefMap);
        System.out.println("\n\n");
    }

}

