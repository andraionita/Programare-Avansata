package com.example.demo;
/**
* @author Ionita Andra Paula, grupa 2A7
*/
import java.util.*;

public class Player {
    public String name;
    public int id;


    public Player(int id, String name) {
        this.name = name;
        this.id = id;

    }
public Player(){

}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
