import java.awt.*;
import java.io.*;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * @author Andra Ionita Grupa 2A7
 * Laborator 5 Compulsory
 * Implementare clasa Catalog + metodele aferente.
 */
public class Catalog implements Serializable {

    private String name;
    private String path;
    private static List<Document> documents = new ArrayList<>();

    public Catalog() {

    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public Document findById(String id) {
        return documents.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", documents=" + documents +
                '}';
    }

    public void addEntry(List<Document> temp) {
        documents=temp;
    }


    public List<Document> getEntries() {
        return documents;
    }
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

    public static void view(String doc) throws IOException {
        try {
            if (doc.startsWith("http")) {
                URI uri = new URI(doc);
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri);
            } else {
                Desktop.getDesktop().open(new File(doc));
            }
        } catch (Exception ex) {
        }
    }

    public static void saveText(String path) throws IOException {
        try{
            BufferedWriter scrie = Files.newBufferedWriter(Paths.get(path));
            scrie.write(documents.size()+"\n");
            for(Document doc:documents)
            {
                scrie.write(doc.getPath().toString()+"\n");
                scrie.write(doc.getTags().size()+"\n");
                for( Map.Entry<String,Object> entry:doc.getTags().entrySet()){
                    scrie.write(entry.getKey()+"\n"+entry.getValue()+"\n");
                }
            }
            scrie.flush();
            scrie.close();

        }catch(Exception a){};
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

        } catch (IOException e) {
     
        }
        return catalog;
    }


}
