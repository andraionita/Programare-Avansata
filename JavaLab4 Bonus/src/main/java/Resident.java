/**
 * @author : Ionita Andra, grupa 2A7
 */

public class Resident {
    private String name;

    public Resident() {
    }

    public Resident(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
