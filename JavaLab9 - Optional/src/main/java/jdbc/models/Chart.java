package jdbc.models; 
/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

import jdbc.controllers.ChartController;
import db.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Chart {

    private List<models.Artist> artistList = new ArrayList<>();
    public Database db;

    public Chart(Database db) {
        this.db = db;
    }

    public void printTop() {
        System.out.println("Top Artisti in tabela chart dupa numarul de vanzari sunt:");
        int index = 0;
        for (models.Artist artist : artistList) {
            index++;
            System.out.println(index + ". " + artist.getName() + " " + artist.getSales());
        }
    }

    public void updateChartToLive() throws SQLException, ClassNotFoundException {
        ChartController chartController = new ChartController(db);
        chartController.updateChart();
        artistList.removeAll(artistList);
        artistList.addAll(chartController.getChart());
    }

    public List<models.Artist> getArtistList() {
        return artistList;
    }
}