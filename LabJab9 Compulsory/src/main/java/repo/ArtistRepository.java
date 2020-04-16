package repo;
import entity.Artist;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 9 Compulsory
 * In clasa ArtistRepository am implementat metodele create, findById si findByName
 */
public class ArtistRepository {


    public static void create(EntityManager entityManager, int id, String name, String country){

        Artist artist = new Artist();
        artist.setId(id);
        artist.setCountry(country);
        artist.setName(name);
        entityManager.persist(artist);
       entityManager.getTransaction().commit();
        System.out.println("\nAm inserat in tabela Artist ");
    }

    public static Artist findById(EntityManager entityManager, long id){
        Artist artist =entityManager.find(Artist.class, id);
        System.out.println(artist.toString());
        return artist;
    }

    public static List<Artist> findByName(EntityManager entityManager, String name){

            List<Artist> artists = (List<Artist>) entityManager.createNamedQuery("Artist.findByName").setParameter("inputname",name).getResultList();
            return artists;
        }

}
