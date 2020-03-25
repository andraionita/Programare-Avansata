import java.util.ArrayList;
/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 2 Bonus
 */
public class Tour {
    private Vehicle vehicle;
    private Client[] clients;

    public Tour(Vehicle vehicle, Client[] clients) {
        this.vehicle = vehicle;
        this.clients = clients;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client[] getClients() {
        return clients;
    }

    public void setClients(Client[] clients) {
        this.clients = clients;
    }
}
