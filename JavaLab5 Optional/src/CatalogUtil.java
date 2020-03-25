import java.awt.*;
import java.io.*;
import java.lang.Object;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

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


    public static void saveText(String path) throws IOException {

        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fos);
       ;
    }

    public static Catalog loadText(String path) throws InvalidCatalogException, IOException {
        Catalog catalog = new Catalog();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            int catalogSize = Integer.parseInt(reader.readLine());
            for (int i=0; i<catalogSize;i++) {
                Document document = new Document(reader.readLine());
                int tagsNr = Integer.parseInt(reader.readLine());
                for (int j=0; i<tagsNr; i++){
                    document.addTag(reader.readLine(),reader.readLine());
                }
                document.setId(reader.readLine());
                catalog.add(document);
            }
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Path not found");
        }
        return catalog;
    }

    public static void viewText(Document doc) throws IOException {
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
