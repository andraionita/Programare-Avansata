/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

package factory;


// abstract factory va crea obiectele DAO

import jpa.repo.AbstractRepository;

public interface RepoAbstractFactory {

    public AbstractRepository createRepository(String type);
}
