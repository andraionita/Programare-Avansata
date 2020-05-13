package com.example.demo.controller;

/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */

import com.example.demo.entity.Game;
import com.example.demo.repository.GamesRepo;
import com.example.demo.services.GameService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    ArrayList<Game> games = new ArrayList<>();

    public GameController() {
    }

    @GetMapping
    public List<Game> getGames() {
        return GamesRepo.showList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable("id") String id) {
        Game game = GameService.getGame(id);
        return new ResponseEntity<Game>(game, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game game1 = GameService.createGame(game);
        games.add(game);
        return new ResponseEntity<Game>(game1, new HttpHeaders(), HttpStatus.CREATED);
    }
}
