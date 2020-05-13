package com.example.demo.services;

import com.example.demo.Player;
import com.example.demo.repository.PlayersRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ionita Andra Paula, 2A7
 * Laborator 11 Optional
 */
public class PlayerService {

    public static List<Player> getAllPlayers() {
        List<Player> players = PlayersRepo.showList();
        if (players.size() > 0) {
            return players;
        } else {
            return new ArrayList<>();
        }
    }

    public static Player getPlayer(String playerId) {
        Player player = PlayersRepo.showPlayer(playerId);
        if(player!=null)
            return player;
        else throw new StackTrace("Player-ul cu id-ul " + playerId+" nu a fost gasit");
    }

    public static Player createPlayer(Player player) {
        PlayersRepo.addPlayer(player);
        return player;
    }

    public static String deletePlayer(String playerId) {
        if (PlayersRepo.showPlayer(playerId) != null) {
            PlayersRepo.deletePlayer(playerId);
            return "Succes";
        } else throw new StackTrace("Player-ul nu a fost gasit");
    }

    public static String updatePlayer(String playerId, String newName) {
        if (PlayersRepo.showPlayer(playerId) != null) {
            PlayersRepo.updatePlayer(playerId, newName);
            return "Succes";
        } else throw new StackTrace("Player-ul nu a fost gasit");
    }
}
