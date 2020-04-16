import java.beans.Statement;
import java.sql.*;
/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In aceasta clasa am scris constructorul, getter si setter pentru clasa ArtistController
*/
public class ArtistController {

    private String name;
    private int id = 1;
    private String country;
    public Database db;
    public Connection conectam;

    public ArtistController() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        this.conectam = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
