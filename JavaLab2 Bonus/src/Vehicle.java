import java.util.Objects;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 2 Bonus
 * In loc sa folosim enum, vom crea clase dedicate pentru car, trucks si drones iar vehicle devina abstract
 * Fiecare vehicul apartine unui singur depozit.
 * Fiecare vehicul face un singur tur 
 */

public abstract class Vehicle {

    public String name;
    private Depot depot;
    private VehicleType type;

    public Vehicle() { }

    public Vehicle(String name) {
        this.name = name;
    }

    public Depot getDepot() {
        return this.depot;
    }

    void setDepot(Depot depot) {
        this.depot = depot;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getName().equals(vehicle.getName());
    }

}
