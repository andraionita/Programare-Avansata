package KnapsackProblem;

/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus
 */
public interface Item {
    String getName();

    double getValue();

    double getWeight();

    default double profitFactor() {
        return getValue() / getWeight();
    }
}