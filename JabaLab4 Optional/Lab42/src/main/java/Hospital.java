
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.lang.Comparable;

/**
 * @author Andra Ionita Grupa 2A7
 */

public class Hospital implements Type {

    private String name;
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hospital(String name, int capacity){
        this.name=name;
        this.capacity=capacity;

    }


    public Hospital(String name){
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return  name ;

    }

    public void decreaseCapacity() {
        this.capacity-=1;
    }
}
