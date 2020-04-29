package db; 

/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

import java.sql.*;

//Database Singleton
public class Database {
    public ResultSet rs;
    private Connection con;

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() { //stabilim conexiunea
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
            System.out.println("Suntem conectati la MusicAlbums");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet setResultSet(String query) { //executam query-uri
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void doUpdate(String update) { //actualizam
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PreparedStatement createPreparedStatement(String string) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement(string);
            return preparedStatement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getCon() {
        return con;
    }

}
