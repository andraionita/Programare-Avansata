import java.io.IOException;
import java.util.Scanner;
/**
 * @author Ionita Andra Paula din grupa 2A7
 * Laboratorul 5 Optional
 * In clasa LoadCommand vom implementa o singura metoda si anume cea care ofera functionalitatea variantei "load" oferite ca input in shell.
 * Deci, cu un for vom trece prin fiecare document si vom incarca in consola continutul sau.
 */
public class LoadCommand implements Command {
    public static void startCommand(Scanner scanner) throws IOException, InvalidCatalogException {
        System.out.println("Scrie locatia documentului");
        Catalog catalog1 = CatalogUtil.loadText(scanner.next());
        System.out.println("Documentul gasit la acest catalog este: ");
        for(Document d : catalog1.getDocuments())
            System.out.println(d.getLocation() + " with tags: " + d.getTags());
        //CatalogUtil.View(doc);
    }
}