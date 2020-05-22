/**
 * @author : Ionita Andra, grupa 2A7*/
package HR;

public interface Type extends Comparable {

    String getName();

    public default int compareTo(Object o) {
        if (o instanceof Type) {
            return this.getName().compareTo(((Type) o).getName());
        }
        return 0;
    }

}