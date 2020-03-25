package KnapsackProblem;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus
 */
import java.util.Comparator;

public class SortByName implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getName().compareTo(b.getName()) > 0) return 1;
        else if (a.getName() == b.getName()) return 0;
        else return -1;
    }
}
