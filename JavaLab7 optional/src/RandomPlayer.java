import java.util.Random;
/**
 * @Author: Ionita Andra, grupa A7
 * Laboratorul 7 Optional
 * In clasa aceasta alegem random un token din cei existenti.
 */

public class RandomPlayer extends Player {

    public RandomPlayer( int id, Board board) {
        super(id, board);
    }


    @Override
    public void run() {
        Random rand=new Random();
        while (!board.gameOver) {
            ExtractToken(rand.nextInt(board.bound));
        }
        terminated = true;
        Thread.currentThread().stop();
        return;
    }
}