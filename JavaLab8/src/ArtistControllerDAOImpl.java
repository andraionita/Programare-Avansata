import java.sql.*;
/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In clasa aceasta am implementat functiile definite in interfata ArtistControllerDAO
*/
public class ArtistControllerDAOImpl implements ArtistControllerDAO {

    private String name;
    private int id = 1;
    private String country;

    public Connection conectam;

    public ArtistControllerDAOImpl() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        this.conectam = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
    }

    public void create(String name, String country) throws SQLException {
        String sql = "INSERT INTO Artists(name,country) VALUES(?,?)";


        try (Connection conn = this.conectam; PreparedStatement pstmt = conectam.prepareStatement(sql)) {

            // pstmt.setInt(1, id);
            pstmt.setString(1, name);
            pstmt.setString(2, country);
            pstmt.executeUpdate();


        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Am adaugat o noua linie in tabela artist");
    }

    public void findByName(String name) throws SQLException {

        java.sql.Statement comanda = conectam.createStatement();

        System.out.println("\n");
        ResultSet rezultat = comanda.executeQuery("Select id, name, country from Artists where name like '" + name+"'");
        System.out.println("La numele "+name+" am gasit urmatoarele intrari in tabela artist:");
        while (rezultat.next())
            System.out.println(rezultat.getInt(1) + "  " + rezultat.getString(2) + "  " + rezultat.getString(3));

    }

    @Override
    public void showAll() throws SQLException {
        java.sql.Statement comanda = conectam.createStatement();

        System.out.println("\n");
        ResultSet rezultat = comanda.executeQuery("Select id, name, country from Artists");
        System.out.println("Tabelul Artist contine urmatoarele intrari:");
        while (rezultat.next())
            System.out.println(rezultat.getInt(1) + "  " + rezultat.getString(2) + "  " + rezultat.getString(3));

    }


    @Override
    public String toString() {
        return "ArtistControllerDAOImpl{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}
