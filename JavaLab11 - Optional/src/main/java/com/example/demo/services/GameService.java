package com.example.demo.services;

/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */

import com.example.demo.entity.Game;
import com.example.demo.repository.GamesRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    public static List<Game> getAllGames() {
        List<Game> games = GamesRepo.showList();
        if (games.size() > 0) {
            return games;
        } else {
            return new ArrayList<>();
        }
    }

    public static Game getGame(String gameId) {
        Game game = GamesRepo.showGame(gameId);
        if(game!=null)
            return game;
        else throw new CustomException("Jocul cu id-ul " + gameId+" nu a fost gasit");
    }

    public static Game createGame(Game game) {
        GamesRepo.addGame(game);
        return game;
    }

    public static String deleteGame(String gameId) {
        if (GamesRepo.showGame(gameId) != null) {
            GamesRepo.deleteGame(gameId);
            return "Succes";
        } else throw new CustomException("Jocul nu a fost gasit");
    }
}
