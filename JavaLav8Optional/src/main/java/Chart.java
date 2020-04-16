import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 8 Optional
 * Aici am implementat clasa chart care are 2 metode: una care apeleaza functia de inserare in chartController si cea care salveaza datele
 * relevante pentru a crea ranking-ul, si o metoda care afiseaza rankingul efectiv
 */
public class Chart {

    List<Artist> artistList = new ArrayList<>();
    public Database db;

    public Chart(Database db) {
        this.db = db;
    }

    public void updateChart() throws SQLException {

        ChartController chartController = new ChartController(db);
        chartController.updateChart();
        artistList.removeAll(artistList);
        artistList.addAll(chartController.getAll());
    }

    public void showAll() {
        System.out.println("Topul Artistilor in functie de vanzari:");
        int i= 0;
        for (Artist artist : artistList) {
            i++;
            System.out.println(i + " - " + artist.getName() + " a vandut albume in valoare de" + artist.getSales()+" dolari");
        }
    }


}
