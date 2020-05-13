package com.example.demo.controller;
/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */

import com.example.demo.Player;
import com.example.demo.repository.PlayersRepo;
import com.example.demo.services.PlayerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/players")
public class PlayerController {
    ArrayList<Player> players = new ArrayList<>();

    public PlayerController() {
    }

    @GetMapping
    public List<Player> getPlayers() {
        return PlayersRepo.showList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable("id") String id) {
        Player player = PlayerService.getPlayer(id);
        return new ResponseEntity<Player>(player, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPlayer(@RequestParam("name") String name) {
        Random rand = new Random();
        String id = String.valueOf(rand.nextInt(9999));
        players.add(new Player(id, name));
        PlayersRepo.addPlayer(id, name);
        return new ResponseEntity<>(
                "Player-ul cu id-ul " + id +" a fost adaugat cu succes ", HttpStatus.CREATED);
    }


    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<Player>
    createPlayer(@RequestBody Player player) {
        Player player1 = PlayerService.createPlayer(player);
        players.add(player);
        return new ResponseEntity<Player>(player1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlayer(
            @PathVariable String id, @RequestParam("name") String name) {
        PlayersRepo.updatePlayer(id, name);
        return new ResponseEntity<>(
                "Numele player-ului a fost actualizat cu succes cu numele " + name, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable String id) {
        String response = PlayerService.deletePlayer(id);
        return new ResponseEntity<String>(response, new HttpHeaders(), HttpStatus.OK);
    }
}
