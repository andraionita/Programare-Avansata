/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 * cu getType vom citi din input.txt ce tip de implementare vom folosi, fie jpa fie jdbc.
 * In functie de tipul scris acolo AlbumRepositoryFactory va crea un obiect fie cu clasele din pachetul jpa fie cu clasele din pachetul jdbc
 * Ambele metode functioneaza.
 */

package app;

import db.Database;
import factory.AlbumRepositoryFactory;
import factory.ArtistRepositoryFactory;
import factory.ChartRepositoryFactory;
import jpa.entity.Album;
import jpa.entity.Artist;
import jpa.entity.Chart;
import jpa.repo.AbstractRepository;
import jpa.repo.ChartRepository;
import jpa.util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.*;

public class AlbumManager {
    private static db.Database db;

    public static void main(String[] args) {
        EntityManagerFactory factory = PersistenceUtil.getInstance();
        EntityManager entityManager = factory.createEntityManager();

        String param = getType();
        init(param);

		// va crea o entita si va insera in tabela prin metoda jdbc
        
        AlbumRepositoryFactory albumRepositoryFactory = new AlbumRepositoryFactory();
        AbstractRepository<Album> albumRepository = albumRepositoryFactory.createRepository(param);
        Album example = new Album();
        example.setArtistId(1301);
        example.setId(890);
        example.setName("Dancing on the java homework");
        example.setReleaseYear((long) 2011);
        albumRepository.create(example);


        ArtistRepositoryFactory artistRepositoryFactory = new ArtistRepositoryFactory();
        AbstractRepository<Artist> artistRepository = artistRepositoryFactory.createRepository(param);
        Artist example1 = new Artist();

        example1.setId(30000);
        example1.setName("Shaneelee");
        example1.setCountry("Uganda");
        artistRepository.create(example1);


        ChartRepositoryFactory chartRepositoryFactory = new ChartRepositoryFactory();
        AbstractRepository<Chart> chartRepository = chartRepositoryFactory.createRepository(param);
        Chart example2 = new Chart();

        example2.setArtistId(30000);
        example2.setSales((int) (Math.random() * 1000));

        chartRepository.create(example2);

        entityManager.close();
        factory.close();


    }

    private static String getType() {
        String type = "";
        try {
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            type = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return type;
    }

    private static void init(String type) {
        if (type.equals("jdbc"))
            db = Database.getInstance();
    }

    public static Database getDb() {
        return db;
    }
}