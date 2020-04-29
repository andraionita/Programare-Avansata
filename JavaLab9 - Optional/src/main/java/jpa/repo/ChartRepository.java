/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */
package jpa.repo;

import jpa.entity.Chart;

public class ChartRepository extends AbstractRepository<Chart> {

    public ChartRepository() {
        super();
    }

    public Chart findById(int id) {
        return entityManager.find(Chart.class, id);
    }

}
