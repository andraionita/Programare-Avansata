/**
 * @author: Calin Irina, I2E2
 */

import java.sql.*;
/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 8 Optional
 * Clasa Database Singleton ce contine metoda care face conexiunea cu baza de date.
 */

public class Database  {
    public ResultSet rezultat;
    private Connection conectam;

    private static Database instance = new Database();

    public static Database getInstance() {
        return instance;
    }

    private Database() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conectam = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public ResultSet setResultSet(String comanda) {
        try {
            Statement stmt = conectam.createStatement();
            rezultat = stmt.executeQuery(comanda);
        } catch (Exception e) {
           e.printStackTrace();
        }
        return rezultat;
    }



    public PreparedStatement createPreparedStatement(String string) {
        try {
            PreparedStatement preparedStatement = conectam.prepareStatement(string);
            return preparedStatement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
