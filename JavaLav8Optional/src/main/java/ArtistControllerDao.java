import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 8 Optional
 *  Aici am implementat metoda create care insereaza in artist si metoda find by name care cauta artistul dupa numele dat
 */

public class ArtistControllerDao {
    Database db;

    public ArtistControllerDao(Database db) {
        this.db = db;
    }

    public void create(String name, String country) {
        String comanda = "INSERT INTO ARTISTS(NAME,COUNTRY) VALUES(?,?)";
        try {
            PreparedStatement pstmt = db.createPreparedStatement(comanda);
            pstmt.setString(1,  name);
            pstmt.setString(2, country);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void findByName(String name) {
        ResultSet rezultat = db.setResultSet("SELECT id from artists where name = '" + name + "'");
        try {
            while (rezultat.next())
                System.out.println( rezultat.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
