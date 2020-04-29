package jdbc.controllers; 

/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import db.Database;

public class ChartController {
    Database db;
    List<Integer> artistsID = new ArrayList<>();

    public ChartController(Database db) {
        this.db = db;
    }

    public void updateChart() throws SQLException, ClassNotFoundException {
        artistsID.removeAll(artistsID);
        try {
            ResultSet resultSet = db.setResultSet("(SELECT ID FROM ARTISTS) MINUS (SELECT ARTIST_ID FROM CHART)");
            while (resultSet.next()) {
                artistsID.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        insertSales();
        System.out.println("Chart actualizat");
    }


    private void insertSales() {
        if (!artistsID.isEmpty()) {
            for (Integer id : artistsID) {
                try {
                    PreparedStatement pstmt = db.createPreparedStatement("INSERT INTO CHART(artist_id,sales) " +
                            "VALUES(?,?)");
                    pstmt.setInt(1, id);
                    pstmt.setInt(2, (int) (Math.random() * 1000));
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    e.getStackTrace();
                }
            }
        }
    }

    public List<models.Artist> getChart() throws SQLException, ClassNotFoundException {
        List<models.Artist> artists = new ArrayList<models.Artist>();
        ResultSet resultSet = db.setResultSet("SELECT A.ID,A.NAME,A.COUNTRY,C.SALES FROM ARTISTS A,CHART C WHERE A.ID=C.ARTIST_ID ORDER BY C.SALES DESC ");
        while (resultSet.next()) {
            models.Artist artist = new models.Artist(resultSet.getString(2), resultSet.getString(3), db);
            artist.setId(resultSet.getInt(1));
            artist.setSales(resultSet.getInt(4));
            artists.add(artist);
        }
        return artists;
    }
}