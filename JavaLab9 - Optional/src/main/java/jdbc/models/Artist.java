package models; 

/**
 * @author Ionita Andra, 2A7
 * Laboratorul 9 - Optional
 */

import db.Database;

import java.sql.*;

public class Artist {

    public String name;
    private int id;
    private String country;
    public int sales;
    private Database db;

    public Artist(String name, String country, Database db) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.country = country;
        this.db = db;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getName() {
        return name;
    }


    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
