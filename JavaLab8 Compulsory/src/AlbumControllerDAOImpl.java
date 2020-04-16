import java.sql.*;
/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In clasa aceasta am implementat functiile definite in interfata AlbumControllerDAO
*/
public class AlbumControllerDAOImpl implements AlbumControllerDAO {

    public int id = 10;
    private String name;
    private int artist_id;
    private int release_year;


    public Connection conectam;

    public AlbumControllerDAOImpl() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

   this.conectam = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
    }

    public void create(String name, int artistId, int releaseYear) throws SQLException {
        String sql = "INSERT INTO Albums(name,artist_id,release_year) VALUES(?,?,?)";
        id=id+1;

        try(Connection conn = this.conectam; PreparedStatement pstmt = conn.prepareStatement(sql)){

            //   pstmt.setInt(1, id);
            pstmt.setString(1, name);
            pstmt.setInt(2, artistId);
            pstmt.setInt(3, releaseYear);
            pstmt.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Am adaugat o noua linie in tabela album.");
    }

    public void findByArtist(int artistId) throws SQLException {

        Statement comanda = conectam.createStatement();

        System.out.println("\n");
        ResultSet rezultat = comanda.executeQuery("Select id, name, release_year from Albums where artist_id=" + artistId);
        System.out.println("La id-ul "+artistId+" am gasit urmatoarele intrari in tabela album:");
        while (rezultat.next())
            System.out.println(rezultat.getInt(1) + "  " + rezultat.getString(2) + "  " + rezultat.getInt(3));
    }

    @Override
    public void showAll() throws SQLException {


        Statement comanda = conectam.createStatement();

        System.out.println("\n");
        ResultSet rezultat = comanda.executeQuery("Select id, name, artist_id, release_year from Albums");
        System.out.println("Tabelul Album contine urmatoarele intrari:");
        while (rezultat.next())
            System.out.println(rezultat.getInt(1) + "  " + rezultat.getString(2)+" " +rezultat.getInt(3) +   "  " + rezultat.getInt(4));

    }
}
