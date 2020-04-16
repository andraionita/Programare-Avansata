import com.github.javafaker.Faker;

import java.sql.SQLException;

import java.util.Random;

/**
 * @author Ionita Andra, grupa 2A7
 * Laboratorul 8 Optional
 * In main am generat cu faker mai multi artisti si i-am inserat in tabela artisti, si mai multe albume pe car ele-am inserat in tabela albums;
 * Pe la final apelamm functia care insereaza in chart si afiseaza ranking-ul artistilor dupa vanzari
 */

public class Main {
    public static void main(String[] args) throws SQLException {

        Faker faker = new Faker();
        Database db = Database.getInstance();
        ArtistControllerDao artistController = new ArtistControllerDao(db);
        AlbumControllerDao albumController = new AlbumControllerDao(db);
        Random rand=new Random();

         try {

                for (int i = 0; i < 1000; i++) {
                    artistController.create(faker.name().fullName(), faker.country().name());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        try {
            for (int i = 0; i < 1000; i++) {
                    albumController.create(faker.funnyName().name(), rand.nextInt(1000)+1, rand.nextInt(2020-1950+1)+1950);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        Chart chart = new Chart(db);
        chart.updateChart();
        chart.showAll();
    }

}


