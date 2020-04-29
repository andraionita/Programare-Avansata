/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

package jpa.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static EntityManagerFactory instance;

    private PersistenceUtil() {
        instance = Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }

    public static EntityManagerFactory getInstance() {
        if (instance == null)
            new PersistenceUtil();
        return instance;
    }
}