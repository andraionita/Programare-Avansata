package server;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Ionita Andra 2A7
 * Clasa pentru user
 */
public class User {
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    public User() {
        this.username = new SimpleStringProperty("Andra");
        this.password = new SimpleStringProperty("printesa");
    }

    public User(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    public String getUserName() {
        return username.get();
    }

    public void setUserName(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
