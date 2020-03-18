import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * @author Andra Ionita Grupa 2A7
 * Laborator 5 Compulsory
 * Implementare clasa main, testCreateSave si testLoadView
 */
public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        try {
            app.testCreateSave();
        } catch (IOException e) {
        }
        app.testLoadView();
    }
    private void testCreateSave() throws IOException {

        //////////////////////initializare catalog si document
		/////////////////a se da path-ul corect pentru fisierul catalog.txt

        Catalog catalog = new Catalog("Java Resources", "e:/desktop/javalab5/src/catalog.txt");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");

        System.out.println("Catalogul este la: " + catalog.getPath());
        System.out.println("Doc-ul este la: " + doc.getLocation());

        doc.addTag("type", "Slides");
        catalog.add(doc);

        ///////se apeleaza functia save

        CatalogUtil.save(catalog);

        System.out.println("Catalogul meu contine " + catalog.getDocuments());
    }

    private void testLoadView() {
        try {
            /////apelare functia load
            /////////////////a se da path-ul corect pentru fisierul catalog.txt
            Catalog catalog = CatalogUtil.load("e:/desktop/javalab5/src/catalog.txt");
            System.out.println("#2####Catalogul meu este: " + catalog.getDocuments());
            Document doc = catalog.findById("java1");
            System.out.println("java 1 este la: " + doc.getLocation());

            ////apelare functia view

            CatalogUtil.view(doc);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (InvalidCatalogException e) {
        }
    }

}