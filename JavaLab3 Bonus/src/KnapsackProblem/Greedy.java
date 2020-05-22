package KnapsackProblem;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus
 */
import java.util.Collections;
import java.util.List;

public class Greedy implements Algorithm {
    private Knapsack knapsack;
    private List<Item> items;

    public Greedy(Knapsack knapsack, List<Item> items) {
        this.knapsack = knapsack;
        this.items = items;
    }

    public void FillKnapsack() {
		//sortam itemele 
        Collections.sort(items, new SortByValue());
        Item[] itemsArray = new Item[items.size()];
        itemsArray = items.toArray(itemsArray);
        for (Item i : itemsArray) {
            if (knapsack.getCapacity() - i.getWeight() >= 0) {
                knapsack.addItem(i);
                knapsack.decreaseCapacity(i);
            }
        }
        System.out.println(knapsack.toString());
    }
}
