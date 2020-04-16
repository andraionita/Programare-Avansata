import java.beans.Statement;
import java.sql.*;

/**
 * @author Ionita Andra, grupa A7
 * Laboratorul 8 Compulsory
 * In aceasta clasa am scris constructorul, getter si setter pentru clasa ArtistController
 */
public class Artist {

    private String name;
    private int id;
    private String country;
    private int sales;
    public Database db;

    public Artist(String name, String country, Database db){
        this.name = name;
        this.country = country;
        this.db = db;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

}
