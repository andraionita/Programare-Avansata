

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 7 Compulsory
 * In clasa game avem constructorul, un getter pentru marimea progresiei si inca o functie de start care creaza
 * pentru fiecare player cate un thread de joc.
 */

public class Game {
    private static int progressionSize;
    int nrPlayers;
    Board board;

    public Game(int progressionSize, int players, Board board) {
        this.progressionSize = progressionSize;
        this.nrPlayers = players;
        this.board = board;

    }

    public static int getProgressionSize(){
        return progressionSize;
    }


    public void Start() {
        var players = IntStream.rangeClosed(1,nrPlayers).mapToObj(i-> new Player(i,board)).toArray(Player[]::new);
        for (Player p : players)
        {
            new Thread(p).start();
        }
    }
}
