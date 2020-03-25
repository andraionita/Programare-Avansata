import java.io.IOException;
import java.util.Scanner;
/**
 * @author Ionita Andra Paula din grupa 2A7
 * Laboratorul 5 Optional
 * In clasa Shell vom implementa o singura metoda de initializare a shell-ului interactiv. 
 * Vom citi de la tastatura una dintre comenzile exit, html, list, view sau load.
 * Dupa cum ii zice si numele, varianta "exit" va inchide sesiunea.
 * Pentru celelalte variante se vor apela clasele coresunzatoare. Pentru mai multe detalii legate de ele si implementarea lor
 * se afla in clasa cu numele respectiv.
 * Conditia cond se refera la conditia de quit care initial e falsa si devine true cand se alege "exit" din lista.
 */
public class Shellu {

    public void start() throws IOException, InvalidCatalogException {
        boolean cond = false;
        Catalog cat = new Catalog();
        Scanner scanner = new Scanner(System.in);
        String comand;
        System.out.println("\n");
        System.out.println("Alege o comanda dintre exit, html, list, view si load:");
        while (!cond) {
            comand = scanner.next();
            switch (comand) {
                case "exit": {
                    cond = true;
                    break;
                }
                case "load": {
                    LoadCommand.startCommand(scanner);

                    break;
                }
                case "view": {
                    ViewCommand.startCommand(cat, scanner);
                    break;
                }
                case "list": {
                    ListCommand.startCommand(cat);
                    break;

                }
                case "html": {
                    HtmlCommand.startCommand(scanner);
                    break;
                }
                default: {
                    break;
                }
            }
        }

    }
}
