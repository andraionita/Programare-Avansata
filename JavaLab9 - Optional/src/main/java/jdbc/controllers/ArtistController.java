package controllers; 

/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */
import java.sql.ResultSet;

import db.Database;

//DAO

public class ArtistController {
    Database db;

    public ArtistController(Database db) {
        this.db = db;
    }

    public void create(String name, String country) {
        String query = "INSERT INTO artists(name, country) values('" + name + "','" + country + "')";
        db.doUpdate(query);
        System.out.println(name + " a fost adaugat in tabela artist.");
    }

    public void findByName(String name) {
        ResultSet rs = db.setResultSet("SELECT id from artists where name = '" + name + "'");
        try {
            while (rs.next())
                System.out.println("ID for " + name + ": " + rs.getInt(1));
        } catch (Exception e) {
            System.out.println("Numele nu exista");
        }
    }

    public int getId(String name) {
        ResultSet rs = db.setResultSet("SELECT id from artists where name = '" + name + "where rownum<2'");
        try {
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
