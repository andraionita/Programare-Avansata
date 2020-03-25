/**
 * @author Ionita Andra Paula din grupa 2A7
 * Laboratorul 5 Optional
 * In clasa ListCommand am creat o singura metoda care implementeaza functionalitatea variantei "list" data ca input in shell.
 * In principiu cu un for o trecem print totate documentele existente intr-un catalog si le vom afisa numele si locatia in terminal
 */
 
public class ListCommand implements Command {
    public static void startCommand(Catalog catalog){
        if(catalog.getEntries().size()==0){
    System.out.println("Catalogul este gol");


        }
        System.out.println("\n");
        for(Document document:catalog.getEntries()){
            System.out.println("Numele documentului este:  "+document.getName());
            System.out.println("Calea documentului este:  "+document.getPath());
        }
    }


}
