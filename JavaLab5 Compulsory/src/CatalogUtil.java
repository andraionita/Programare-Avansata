import java.awt.*;
import java.io.*;
import java.lang.Object;
import java.net.URI;
import java.net.URISyntaxException;
/**
 * @author Andra Ionita Grupa 2A7
 * Laborator 5 Compulsory
 * Implementare clasa CatalogUtil + metodele aferente cerute, anume save, view, load
 * Save v-a salva in fisierul aflat la path-ul dat ca parametru informatii din catalog
 * View va deschide fie un uri in browser fie un fisier in functie de tipul dat ca parametru
 * Load va returna ce se afla la path-ul dat ca parametru.
 */
public class CatalogUtil {
    public static void save(Catalog catalog) throws IOException {
        String path=catalog.getPath();
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(catalog);
    }

    public static Catalog load(String path) throws InvalidCatalogException, IOException {
        Catalog catalog = new Catalog();
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fis);
            catalog = (Catalog) in.readObject();
        }
        catch(ClassNotFoundException e) {

        }
        return catalog;
    }

    public static void view(Document doc) throws IOException {
        try {
            if (doc.getLocation().startsWith("http")) {
                URI uri = new URI(doc.getLocation());
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri);
            } else {
                Desktop.getDesktop().open(new File(doc.getLocation()));
            }
        } catch (Exception ex) {
        }
    }

}
