import java.io.IOException;
import java.util.Scanner;
/**
 * @author Ionita Andra Paula din grupa 2A7
 * Laboratorul 5 Optional
 * Aici este implementata o interfata care va fi implementata de clasele ce reprezinta o comanda pentru shell,
 * si anume: view, load, list si html.
 * Metoda care este mentionata aici este de asemenea si singura metoda care se afla in clasele care extind interfata
 * si anume functia de start Command care implementeaza functionalitatea alegerii trimisa ca input in shell.
 */
public interface Command {
    public static void startCommand(Catalog catalog, Scanner scanner) throws IOException, InvalidCatalogException {};
}
