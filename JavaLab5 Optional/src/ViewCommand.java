import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;
/**
 * @author Ionita Andra Paula din grupa 2A7
 * Laboratorul 5 Optional
 * In clasa ViewCommand vom implementa o singura metoda si anume cea care ofera functionalitatea variantei "view" oferite ca input in shell.
 * Pentru a face view vom cere ca input numele unui fisier si acesta va cauta in catalog daca acel fisier exista.
 * Daca da, se va apela functia view implementata in partea obligatorie. Ca reminder, functia view deschide cu browse fie un url fie un folder/fisier 
 * in functie de path-ul avut.
 */
 
public class ViewCommand implements Command {
    public static void startCommand(Catalog cat, Scanner scan) throws IOException {

        System.out.println("Da numele unui fisier");
        String name=scan.next();
        List<String> names=cat.getEntries().stream().map(entry ->entry.getName()).collect(toList());
        if(names.contains(name)){
           cat.view(name);
        }
    }
}
