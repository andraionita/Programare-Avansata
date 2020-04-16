import javax.xml.crypto.Data;
import java.sql.*;
/**
 * @author Ionita Andra, grupa A7
 * Laboratorul 8 Compulsory
 * In clasa aceasta am implementat constructorul, getter si setter clasei AlbumController
 */
public class Album{
    public int id = 10;
    private String name;
    private int artist_id;
    private int release_year;
    private Database db;

    public Album(String name, int year, int artistID, Database db){
        this.db = db;
        this.name = name;
        this.release_year = year;
        this.artist_id = artistID;
    }


    @Override
    public String toString() {
        return "AlbumController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist_id=" + artist_id +
                ", release_release_year=" + release_year +
                '}';
    }


}

