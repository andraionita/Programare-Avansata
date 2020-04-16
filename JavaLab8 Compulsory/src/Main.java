/**
* @author Ionita Andra, grupa A7
* Laboratorul 8 Compulsory
* In clasa main m-am conectta la baza de date create anterior si am creat 
* spatiul de testing pentru inserarea, selectarii si afisararii datelor din baza de date.
*/
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Database db = new Database();
        db.conn();


        AlbumControllerDAO albumDao=new AlbumControllerDAOImpl();
        ArtistControllerDAO artistDao=new ArtistControllerDAOImpl();

        try{
          //  albumDao.create("When the party is over", 2, 2019);
          //  artistDao.create("Billie Eillish","SUA");
             //albumDao.create("Bucovina Plai cu Flori", 5, 2019);
          // artistDao.create("Luna Amara","Romania");
        }catch (Exception e){};

        try {
         albumDao.findByArtist(5);
         artistDao.findByName("Billie Eillish");
        }catch(Exception a){};


        albumDao.showAll();
       artistDao.showAll();
    }
}
