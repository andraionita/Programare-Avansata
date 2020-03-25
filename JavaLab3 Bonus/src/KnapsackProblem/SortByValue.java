package KnapsackProblem;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus
 */
import java.util.Comparator;

public class SortByValue implements Comparator<Item> {
    public int compare(Item a, Item b) {
        if (a.getValue() < b.getValue()) return 1;
        else if (a.getValue() == b.getValue()) return 0;
        else return -1;
    }
}