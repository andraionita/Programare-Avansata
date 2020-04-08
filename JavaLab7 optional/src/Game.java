import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @Author: Ionita Andra, grupa A7
 * Laborator 7 Optional
 * In aceasta clasa am creat threaduri pentru jucatori si inca un thread pentru timer de tip daemon.
 */

public class Game {
    int nrPlayers;
    Board board;
    Timekeeper timekeeper;
    int sec;

    public Game(int players, Board board, int sec) {
        this.nrPlayers = players;
        this.board = board;
        timekeeper=new Timekeeper(sec);
    }

    public void Start() {


        ManualPlayer p1 = new ManualPlayer(1, board);
        Player p2 = new RandomPlayer(2, board);
        Player p3 = new SmartPlayer(3, board);

        new Thread(p1).start();
        new Thread(p2).start();
        new Thread(p3).start();

        Thread daemon=new Thread(timekeeper);
        daemon.setDaemon(true);
        daemon.start();



        while(true)
            if(isGameFinished()) {
                int p1Prog=p1.getLargestProgression();
                int p2Prog=p2.getLargestProgression();
                int p3Prog=p3.getLargestProgression();
                int arr[]={p1Prog, p2Prog, p3Prog};

                System.out.println("Cea mai mare progresie a Player-ului 1 este de "+arr[0]);
                System.out.println("Cea mai mare progresie a Player-ului 2 este de "+arr[1]);
                System.out.println("Cea mai mare progresie a Player-ului 3 este de "+arr[2]);
                break;
            }
        while(true)
            if(board.gameOver)
                System.exit(0);
    }

    public  boolean isGameFinished(){
        if(timekeeper.isGameOver()){
            System.out.println( "Timpul a expirat!");

            board.gameOver=true;
            timekeeper.stopGame();
            return true;
        }
        else if(board.isGameOver()){
            timekeeper.stopGame();
            return true;
        }
        return false;
    }


}