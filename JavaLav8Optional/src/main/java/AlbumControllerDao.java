import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 8 Optional
 * Aici am implementat metoda create care insereaza in album si metoda find by id care cauta un album dupa id-ul artistului
 */

public class AlbumControllerDao {
    Database db;

    public AlbumControllerDao(Database db) {
        this.db = db;
    }

    public void create(String name, int artistId, int releaseYear) {

        String comanda = "INSERT INTO ARTISTS(name, artist_id, release_year) VALUES(?,?,?)";
        try {
            PreparedStatement pstmt = db.createPreparedStatement(comanda);
            pstmt.setString(1,  name);
            pstmt.setInt(2, artistId);
            pstmt.setInt(3,releaseYear);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void findByArtist(int artistId) {
        ResultSet rezultat = db.setResultSet("SELECT name from albums where artist_id = " + artistId);
        try {
            while (rezultat.next())
                System.out.println("Pentru artistul " + artistId + "s-a gasit urmatoarele albume: " + rezultat.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
