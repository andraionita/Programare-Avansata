import jdk.nashorn.tools.Shell;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.tools.Shell;
/**
 * @author Andra Ionita Grupa 2A7
 * Laborator 5 Optional
 * Implementare clasa main, testCreateSave si testLoadView din Laborator 5 Compulsory
 * In plus fata de partea obligatorie am creat in main o variabila de tip Shellu (dintr-un motiv anume nu ma lasa sa scri Shell simplu)
 * si am apelat functia de start a shell-ului interactiv .
 */
public class Main {
    public static void main(String[] args) throws IOException, InvalidCatalogException {
        Main app = new Main();
        try {
            app.testCreateSave();
        } catch (IOException e) {
        }
        app.testLoadView();

        ////////////////////////////////////////////////////////////////Main pentru Optional///////////////////////////////////////


        Shellu shell=new Shellu();
        shell.start();

        /////////////////////////////final main Optional///////////////////////////////////////////////////////////////////
    }
    private void testCreateSave() throws IOException {

        //////////////////////initializare catalog si document
		/////////////////a se da path-ul corect pentru fisierul catalog.txt

        Catalog catalog = new Catalog("Java Resources", "e:/desktop/catalog.txt");
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
            Catalog catalog = CatalogUtil.loadText("e:/desktop/catalog.txt");
            //System.out.println("#2####Catalogul meu este: " + catalog.getDocuments());
            Document doc = catalog.findById("java1");
            //System.out.println("java 1 este la: " + doc.getLocation());

            ////apelare functia view

            CatalogUtil.view(doc);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } catch (InvalidCatalogException e) {
        }
    }

}