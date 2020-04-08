import java.sql.SQLException;
/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In clasa aceasta am implementat interfata a carei functii vor 
* fi implementate in ArtistControllerDAOImpl 
*/
public interface AlbumControllerDAO {

    public void findByArtist(int artistId) throws SQLException;
    public void create(String name, int artistId, int releaseYear) throws SQLException;
    public void showAll() throws SQLException;

}
