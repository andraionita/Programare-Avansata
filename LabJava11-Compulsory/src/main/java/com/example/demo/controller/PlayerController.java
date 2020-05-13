package com.example.demo.controller;

import com.example.demo.Game;
import com.example.demo.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
* @author Ionita Andra Paula, grupa 2A7
*/

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    Game game=new Game();
    private final List<Player> players = new ArrayList<>();
    public PlayerController() {
        players.add(new Player(1, "Mask"));
        players.add(new Player(2, "Gloves"));
        players.add(new Player(3, "Costica"));
        players.add(new Player(4, "Ionica"));
        game.addPlayer(3,"Costica");
        game.addPlayer(4,"Ionica");
    }
    @GetMapping
    public List<Player> getPlayers() {
        return game.showList();
        //return players;
    }
    @GetMapping("/count")
    public int countPlayers() {
        return players.size();
    }
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") int id) throws SQLException {
        return game.showPlayer(id);
       // return players.stream()
               // .filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public int createPlayer(@RequestParam String name) {
        int id = 1 + players.size();
        players.add(new Player(id, name));
        game.addPlayer(id,name);
        return id;
    }

    @PostMapping(value = "/obj", consumes="application/json")
    public ResponseEntity<String>
    createPlayer(@RequestBody Player player) {
        players.add(player);
        game.addPlayer(player);
        return new ResponseEntity<>(
                "Player-ul a fost adaugat cu succes in joc", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(
            @PathVariable int id, @RequestParam String name) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player-ul nu exista", HttpStatus.NOT_FOUND); //or GONE
        }
        player.setName(name);
        game.updatePlayer(id,name);
        return new ResponseEntity<>(
                "Numele player-ului a fost actualizat cu succes", HttpStatus.OK);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable int id) {
        Player player = findById(id);
        if (player == null) {
            return new ResponseEntity<>(
                    "Player-ul nu exista", HttpStatus.GONE);
        }
        players.remove(player);
        game.deletePlayer(id);
        return new ResponseEntity<>("Player-ul a fost sters", HttpStatus.OK);
    }

    public Player findById(int id){
        Player temp = null;

        for(Player account : players) {
            if(account.getId()==id) {
                temp=new Player(id,account.getName());
            }
        }
        return temp;
    }



}
