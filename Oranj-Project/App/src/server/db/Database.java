package server.db;

import java.sql.*;

/**
 * @author Ionita Andra 2A7
 * clasa bazei de date
 */
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
            System.out.println("Connected to Users Database");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet setResultSet(String query) { //executa un query
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void doUpdate(String update) { //executa updateuri
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

    public Connection getConnection() {
        return con;
    }
}