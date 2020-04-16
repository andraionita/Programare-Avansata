import java.sql.*;
/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In clasa Database m-am conectat la baza de date cu user name-ul "bda" si parola "sql".
*/
public class Database {
    private static Database instance = new Database();
    public Database(){}

    public static Database getInstance(){
        return instance;
    }


    public static void conn() {
        try {
//incarcam clasa cu baza de date
            Class.forName("oracle.jdbc.driver.OracleDriver");

//ne conectam
          Connection conectam = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");

        } catch (Exception eroare) { //////prindem eroarea
            System.out.println(eroare);
        }
    }
}
