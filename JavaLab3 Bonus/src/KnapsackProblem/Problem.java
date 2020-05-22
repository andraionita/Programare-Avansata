package KnapsackProblem;/
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 3 Bonus
 */

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private Knapsack knapsack;
    private List<Item> items;

    public Problem() {
     
		
		//cream o problema random care include generarea unei capacitati si a unui numar de iteme random.
		//poti apela fie greedy fie dynamicprog pt a obtine solutia
        this.knapsack = new Knapsack();
        this.knapsack.setCapacity((int) Math.floor(Math.random() * 80));
        int nrItems = (int) Math.floor(Math.random() * 20);
        this.items = new ArrayList<Item>(nrItems);
        System.out.println("Problema random: Numarul de iteme este " + nrItems + " iar capacitatea este de " + knapsack.getCapacity());
        for (int i = 0; i < nrItems; ++i) {
            int chosenItem = (int) Math.floor(Math.random() * 2);
            //System.out.println(chosenItem);
            switch (chosenItem) {
                case 0:
                    int pages = (int) Math.floor(Math.random() * 500);
                    int value = (int) Math.floor(Math.random() * 25);
                    String name = "Name" + (int) Math.floor(Math.random() * 200);
                    this.items.add(new Book(name, pages, value));
                    //System.out.println(name);
                    break;
                case 1:
                    int weight = (int) Math.floor(Math.random() * 15);
                    String name2 = "KnapsackProblem.Food" + (int) Math.floor(Math.random() * 200);
                    this.items.add(new Food(name2, weight));
                    //System.out.println(name2);
                    break;
                case 2:
                    int weight2 = (int) Math.floor(Math.random() * 15);
                    int value2 = (int) Math.floor(Math.random() * 25);
                    this.items.add(new Weapon(Type.AXE, weight2, value2));
                    //System.out.println(KnapsackProblem.Type.AXE);
                    break;
            }
        }
    }

    public Problem(List<Item> items, Knapsack k) {
        this.items = items;
        this.knapsack = k;
    }

    public Knapsack getKnapsack() {
        return knapsack;
    }

    public void setKnapsack(Knapsack knapsack) {
        this.knapsack = knapsack;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Problem: " + items;
    }
}
