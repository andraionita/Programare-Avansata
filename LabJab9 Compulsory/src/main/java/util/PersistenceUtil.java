package util;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 9 Compulsory
 * In clasa PersistanceUtil care este Singleton creez EntityManagerFactory
 */

public class PersistenceUtil {
    private static EntityManagerFactory instance =Persistence.createEntityManagerFactory("MusicAlbumsPU");

    private PersistenceUtil(){ }

    public static EntityManagerFactory getInstance(){
        return instance;
    }
}