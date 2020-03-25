/**
 * @author Ionita Andra Paula din grupa 2A7
 * Laboratorul 5 Optional
 * In clasa  HtmlCommand am creat o singura metoda care implementeaza functionalitatea variantei "html" data ca input in shell.
 * In principiu o sa incarcam catalogul care a fost creat in main anterior dand path-ul acestuia (sau a altui catalog deja existent)
 * si o sa il incarcam intr-o alta variabila catalog.
 * In continuare tot ce vom face e sa cream un fisier .html la un path dat si sa scriem in el tag-urile clasice de html 
 * si informatii ca nume de document, tag-uri etc.
 * Dupa ce se termina de scris in fisier se va afisa mesajul de terminat in shell.
 */

import java.io.*;
import java.util.Scanner;

public class HtmlCommand implements Command {
    public static void startCommand(Scanner scanner) throws IOException, InvalidCatalogException {
        System.out.println("Scrie locatia catalogului:");
        Catalog catalog1 = CatalogUtil.loadText(scanner.next());
        System.out.println("Scrie locatia unde vrei sa se creeze raportul Html:");
        String outputPath = scanner.next();
        FileWriter fw = new FileWriter(outputPath + "/HTMLRaport.html");
        BufferedWriter out = new BufferedWriter(fw);
        out.write("<!DOCTYPE HTML>");
        out.write("<html>");
        out.write("\t <head>");
        out.write("\t\t <title> Catalog </title>");
        out.write("\t </head>");
        out.write("\t <body>");

        for (Document document : catalog1.getDocuments()) {
            out.write("\t\t<h1> Document 1 </h1>");
            out.write("\t\t<p> ID: " + document.getId() + " </p>");
            out.write("\t\t<p> Tags: " + document.getTags() + " </p>");
        }

        out.write("\t </body>");
        out.write("</html>");
        fw.flush();
        out.flush();
        System.out.println("Un fisier cu denumirea HTMLReport.html a fost creat la " + outputPath + "/HTMLReport.html");
    }
}