
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author Andra Ionita Grupa 2A7
 */

public class Resident implements Type {
    private String name;


    public Resident(String name)
    {
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
        return name ;
    }



}
