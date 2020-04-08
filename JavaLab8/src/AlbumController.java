import java.sql.*;
/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In clasa aceasta am implementat constructorul, getter si setter clasei AlbumController
*/
public class AlbumController {
    public int id = 10;
    private String name;
    private int artist_id;
    private int release_year;


    public Connection conectam;

    public AlbumController() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        this.conectam = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }



    @Override
    public String toString() {
        return "AlbumController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist_id=" + artist_id +
                ", release_year=" + release_year +
                ", conectam=" + conectam +
                '}';
    }


}

