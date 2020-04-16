package repo;
import entity.Album;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 9 Compulsory
 * In clasa AlbumRepository am implementat metodele create, findById, findByName si findByArtist
 */


public class AlbumRepository {
    public static void create(EntityManager entityManager, int id, String name, long release_year, long artist_id){

        Album Album = new Album();
        Album.setId(id);
        Album.setReleaseYear(release_year);
        Album.setName(name);
        Album.setArtistId(artist_id);
        entityManager.persist(Album);
        entityManager.getTransaction().commit();
        System.out.println("\nAm inserat in tabela Album ");
    }

    public static Album findById(EntityManager entityManager, long id){
        Album album =entityManager.find(Album.class, id);
        System.out.println(album.toString());
        return album;
    }

    public static List<Album> findByName(EntityManager entityManager, String name){

        List<Album> artists = (List<Album>) entityManager.createNamedQuery("Album.findByName").setParameter("inputname",name).getResultList();
        return artists;
    }

    public static List<Album> findByArtist(EntityManager entityManager, long id){

        List<Album> albums = (List<Album>) entityManager.createNamedQuery("Album.findByArtist").setParameter("inputname",id).getResultList();
        return albums;
    }
}
