package controllers; 

/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

import java.sql.ResultSet;

import db.Database;
import jpa.repo.AbstractRepository;

//DAO

public class AlbumController extends AbstractRepository {
    Database db;

    public AlbumController(Database db) {
        this.db = db;
    }

    public void create(String name, int artistId, int releaseYear) {
        String query = "INSERT INTO albums(name, artist_id, release_year) values('" + name + "'," + artistId + "," + releaseYear + ")";
        db.doUpdate(query);
        System.out.println(name + " a fost adaugat in tabela album.");
    }

    public void findByArtist(int artistId) {
        ResultSet rs = db.setResultSet("SELECT name from albums where artist_id = " + artistId);
        try {
            while (rs.next())
                System.out.println("Albumele gasite la id-ul artistului " + artistId + " sunt: " + rs.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
