/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 2 Bonus
 */
public class Truck extends Vehicle {
    public Truck() {
    }

    public Truck(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
