/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 2 Bonus
 */
public class Car extends  Vehicle{
    public Car() {
    }

    public Car(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
