package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//Database Singleton
public class Game {
    public ResultSet rs;
    private Connection con;
    public List<Player> players=new ArrayList<Player>();


    public Game() { //stabilim conexiunea
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bda", "sql");
            System.out.println("Suntem conectati la Baza de date cu playeri");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ResultSet setResultSet(String query) { //executam query-uri
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);

        }
        return rs;
    }

    public void doUpdate(String update) { //actualizam
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(update);
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public Connection getCon() {
        return con;
    }

    public void addPlayer(int id, String name){
        String query = "INSERT INTO game(id, name) values('" + id + "','" + name + "')";
        doUpdate(query);
        players.add(new Player(id,name));
        System.out.println(name + " a fost adaugat in game.");
    }

    public void addPlayer(Player player){
        String query = "INSERT INTO game(id, name) values('" + player.getId() + "','" + player.getName() + "')";
        doUpdate(query);
        players.add(new Player(player.getId(),player.getName()));
        System.out.println(player.getName() + " a fost adaugat in game.");
    }


    public void updatePlayer(int id, String newName) {
        String query = "UPDATE game set name=" + newName + "where id=" +id ;
        doUpdate(query);
        System.out.println(id + " a fost actualizat cu "+newName);
    }



    public void deletePlayer(int id) {
        String query = "DELETE FROM game where id=" + id ;
         doUpdate(query);
        System.out.println("Player-ul cu id"+id+" a fost sters");
    }

    public List<Player> showList() {
        Player player = null;
        ResultSet rs = setResultSet("SELECT id, name from game");
        System.out.println("Playerii din joc sunt:\n");
        try {
            while (rs.next()) {
                System.out.println("Player-ul cu id " + rs.getInt(1) + " se numeste " + rs.getString(2));
                player = new Player(rs.getInt(1), rs.getString(2));
                players.add(player);
            }
        } catch (Exception e) {
            System.out.println("Tabela e goala");
        }
        System.out.println("\n");
        return players;
    }

    public Player showPlayer(int id) {
        Player player = null;
        ResultSet rs = setResultSet("SELECT id, name from game where id="+id);
        try {
            while (rs.next()) {
                System.out.println("Player-ul cu id " + rs.getInt(1) + " se numeste " + rs.getString(2));
                player=new Player(rs.getInt(1), rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("Player-ul nu exista");
        }
        return player;
    }
}