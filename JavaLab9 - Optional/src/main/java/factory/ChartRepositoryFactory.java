/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

package factory;

import jpa.repo.AbstractRepository;
import jpa.repo.AlbumRepository;

public class ChartRepositoryFactory implements RepoAbstractFactory {
    @Override
    public AbstractRepository createRepository(String type) {
        if (type.equals("jdbc")) {
            return new controllers.AlbumController(app.AlbumManager.getDb());
        } else if (type.equals("jpa")) {
            return new AlbumRepository();
        } else return null;
    }
}
