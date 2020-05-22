package KnapsackProblem;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus
 */

import java.util.List;

public class DynamicProg implements Algorithm {
    private Knapsack knapsack;
    private List<Item> items;

    public DynamicProg(Knapsack knapsack, List<Item> items) {
        this.knapsack = knapsack;
        this.items = items;
    }

    public void FillKnapsack() {
   
		//respectam algoritmul descris in slideuri
        int capacity = (int) knapsack.getCapacity();
        int nbItems = items.size();
        double[][] m = new double[nbItems + 1][capacity + 1];

        for (int j = 0; j <= capacity; j++) {
            m[0][j] = 0;
        }

        for (int i = 1; i <= nbItems; i++) {
            for (int w = 1; w <= capacity; w++) {

                if (items.get(i - 1).getWeight() > w) {
                    m[i][w] = m[i - 1][w];
                } else {
                    m[i][w] = Math.max(m[i - 1][w], m[i - 1][w - (int) items.get(i - 1).getWeight()] + items.get(i - 1).getValue());
                }
            }
        }
        System.out.println("[Dynamic] Profitul maxim este " + m[nbItems][capacity] + " obtinut cu itemele:");

        do {
            if (m[nbItems][capacity] != m[nbItems - 1][capacity]) {
                System.out.println("\t" + items.get(nbItems-1));
                capacity = capacity - (int) items.get(nbItems - 1).getWeight();
            }
            nbItems--;
        } while (nbItems > 0);
    }
}
