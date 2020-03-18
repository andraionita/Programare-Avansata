
import com.github.javafaker.Faker;
import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

/**
 * @author Andra Ionita Grupa 2A7
 * Clasa main.
 */

public class Main {
    public static void main(String[] args) {


        var r = IntStream.rangeClosed(0, 3).mapToObj(i -> new Resident("R" + i)).toArray(Resident[]::new);
        var h = IntStream.rangeClosed(0, 3).mapToObj(i -> new Hospital("H" + i)).toArray(Hospital[]::new);

        h[0].setCapacity(1);
        h[1].setCapacity(2);
        h[2].setCapacity(2);

        List<Resident> residentList = new ArrayList<>();
        residentList.addAll(Arrays.asList(r));

        Set<Hospital> myHospitals = new TreeSet<Hospital>();
        myHospitals.addAll(Arrays.asList(h));

        List<Resident> newSortedList = residentList.stream().sorted(Comparator.comparing(Resident::getName)).collect(Collectors.toList());

        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();

        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));

        System.out.println("\n Rezidentii si preferintele lor sunt:");
        System.out.println(resPrefMap);


        Map<Hospital, List<Resident>> hosPrefMap = new Hashtable<>();

        hosPrefMap.put(h[0], Arrays.asList(r[3], r[0], r[1], r[2]));
        hosPrefMap.put(h[1], Arrays.asList(r[0], r[2], r[1]));
        hosPrefMap.put(h[2], Arrays.asList(r[0], r[1], r[3]));

        System.out.println("\n Spitalele si preferintele lor sunt:");
        System.out.println(hosPrefMap);


//////////////////Printam rezidentii care au atat spitalul H0 cat si spitalul H2 ca preferinte

        List<Hospital> target1 = Arrays.asList(h[0], h[2]);
        List<Resident> result1 = residentList.stream().filter(res -> resPrefMap.get(res).containsAll(target1)).collect(Collectors.toList());
        System.out.println("\n Rezidentii care au acceptat spitalele H0 si H2:");
        System.out.println(result1.toString());

/////////////////////////afisam spitalele care au rezidentul R0 ca prima optiune

        try {
            System.out.println("\n Spitalele care au pe rezidentul R0 in topul preferintelor:");
            myHospitals.stream().filter(hos -> hosPrefMap.get(hos).get(0).equals(r[0])).forEach(System.out::println);

        } catch (Exception e) {

        }
        System.out.println("\n");

//////////////////////algoritmul de mathcing pentru instanta problemei din laboratorul 4///////////
        System.out.println("Un o solutie pentru match ar fi:");
        Matching t = new Matching();
        t.doMatch(resPrefMap);
        System.out.println("\n");

 //////////////////////////////cream instanta problemei fake.
        Problem pb=new Problem();
        pb.ResProblem();

    }
}