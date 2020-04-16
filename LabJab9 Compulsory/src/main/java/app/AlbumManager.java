package app;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 9 Compulsory
 * In clasa Album Manager am implementat main-ul
 */

import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AlbumManager {
    public static void main(String[] args) {
        EntityManagerFactory factory = PersistenceUtil.getInstance();
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Artist artist = new Artist();
        int id=1;

        ///apelam functia create pentru a insera un artist -> nu se pot face inserari in tabele diferite in acelasi timp
        // din cauza aceasta linia de mai jos trebuie comentata in caz ca vrem sa inseram in album. Pentru testare sa se comenteze linia 46

        ///////////////////////////
          ArtistRepository.create(entityManager,id,"Tupac", "USA");
          /////////////////////////////

        //apelam functia find by id pentru a gasi in tabela artistul cu id-ul respectiv
        Artist temp= ArtistRepository.findById(entityManager,(long)500);
        System.out.println("\nAfisam artistul gasit dupa id: "+temp.getName());

        //apleam functia find by name pentru a gasi in tabela lista cu persoane care au acelasi nume
        List<Artist> temp1 = ArtistRepository.findByName(entityManager, "Billie Eillish");
        System.out.println("\nAfisam lista cu artisti gasite dupa nume: "+temp1);

        id=1;
        Album album = new Album();

        ///apelam functia create pentru a insera un album -> nu se pot face inserari in tabele diferite in acelasi timp
        AlbumRepository.create(entityManager, id,"Eyes on me", 2010, 1);

        //apelam functia find by id pentru a gasi in tabela albumul cu id-ul respectiv
        Album tempa= AlbumRepository.findById(entityManager,(long)27);
        System.out.println("\nAfisam albumul gasit dupa id: "+tempa.getName());

        //apleam functia find by name pentru a gasi in tabela lista cu albume care au acelasi nume
        List<Album> tempb = AlbumRepository.findByName(entityManager, "Dancing all night");
        System.out.println("\nAfisam lista cu albume gasite dupa nume: "+tempb);

        //apleam functia find by artist pentru a gasi in tabela lista cu albumele artistului
        List<Album> tempc = AlbumRepository.findByArtist(entityManager, 2);
        System.out.println("\nAfisam lista cu albume gasite dupa id-ul artistului: "+tempc);
  
        entityManager.persist(artist);
        entityManager.persist(album);


        entityManager.close();
        factory.close();
    }
}
