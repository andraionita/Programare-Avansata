/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 2 Bonus
 */
public class Drone extends Vehicle {
    public Drone() {
    }

    public Drone(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
