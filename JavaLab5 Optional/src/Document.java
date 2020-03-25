
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Andra Ionita Grupa 2A7
 * Laborator 5 Optional
 * Implementare clasa Document + metodele aferente. Nimic in plus fata de partea obligatorie.
 */
public class Document implements Serializable {
    private String id;
    private String name;
    private String location;
    private Map<String, Object> entries = new HashMap<>();

    public Document(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Document(String location){
        this.location = location;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTag(String key, Object obj) {
        entries.put(key, obj);
    }
    public Map<String, Object> getTags(){
        return entries;
    }

    public String getLocation(){
       return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getentries() {
        return entries;
    }

    public void setentries(Map<String, Object> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return  location;
    }

    public String getPath() {
        return location;
    }
}
