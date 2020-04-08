import java.sql.SQLException;
/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In aceasta clasa am creat interfata ArtistControllerDAO care va fi 
* implementata de clasa ArtistControllerDAOImpl
*/
public interface ArtistControllerDAO {

    public void findByName(String name) throws SQLException;
    public void create(String name, String country) throws SQLException;
    public void showAll() throws SQLException;
}
