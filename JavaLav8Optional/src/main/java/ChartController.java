import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 8 Optional
 * Aici am implementat metodele updateChart care va salva lista cu id-uri pentru a sti unde sa faca inserarea,
 * metoda insertSales care va insera date generate random in tabela chart si metoda showAll care va salva numele artistilor
 * si vanzarile in ordinea descrescatoare a vanzarilor
 */

public class ChartController {
    Database db;
    List<Integer> artistsID = new ArrayList<>();

    public ChartController(Database db) {
        this.db = db;
    }

    public void updateChart() {
        artistsID.removeAll(artistsID);
        try {
            ResultSet rezultat = db.setResultSet("(SELECT ID FROM ARTISTS) MINUS (SELECT ARTIST_ID FROM CHART)");
            while (rezultat.next()) {
                artistsID.add(rezultat.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertSales();
    }


    private void insertSales() {
        String sql = "INSERT INTO CHART(artist_id,sales) VALUES(?,?)";
        Random rand=new Random();
        if (!artistsID.isEmpty()) {
            for (Integer id : artistsID) {
                try {
                    PreparedStatement pstmt = db.createPreparedStatement(sql);
                    pstmt.setInt(1, (int) id);
                    pstmt.setInt(2, rand.nextInt(1000000-10000+1)+10000);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    public List<Artist> getAll() throws SQLException {
        List<Artist> artists = new ArrayList<Artist>();
        ResultSet rezultat = db.setResultSet("SELECT ARTISTS.ID,ARTISTS.NAME,ARTISTS.COUNTRY,CHART.SALES FROM ARTISTS ,CHART  WHERE ARTISTS.ID=CHART.ARTIST_ID ORDER BY CHART.SALES DESC ");
        while (rezultat.next()) {
            Artist artist = new Artist(rezultat.getString(2), rezultat.getString(3),db);
            artist.setId(rezultat.getInt(1));
            artist.setSales(rezultat.getInt(4));
            artists.add(artist);
        }
        return artists;
    }
}