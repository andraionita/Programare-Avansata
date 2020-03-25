package KnapsackProblem;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus
 */

public class NaturalSort implements Comparable<Item> {
    Item a;
    public int compareTo(Item b) {
        if (a.getName().compareTo(b.getName()) > 0) return 1;
        else if (a.getName() == b.getName()) return 0;
        else return -1;
    }
}
