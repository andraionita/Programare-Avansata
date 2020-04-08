import java.util.Scanner;
/**
 * @Author: Ionita Andra, grupa A7
 * Laboratorul 7 Optional
 * In aceasta clasa am implementat un jucator manual careia i se va prezenta lista de tokeni 
 * actuala si va alege din aceasta un token.
 */

public class ManualPlayer extends Player {

    public ManualPlayer(int playerID, Board board) {
        super(playerID, board);
    }

    private int getUserToken() {
        Scanner scanner = new Scanner(System.in);
        boolean tokenExists = false;
        int tokenIndex = 0;
        while (board.turn != playerID) {
        } //if it's not my turn, do nothing
        if (!board.isGameOver()) {
            System.out.println("Tokeni disponibili: " + board.tokens);
            System.out.println("[Player " + playerID + "] a acumulat tokenii: " + this.extractedTokens);
            System.out.println("[Player " + playerID + "]: Alege un token dupa index-ul sau incepand cu 1: ");
            while (!tokenExists && !board.isGameOver())
                try {
                    tokenIndex = Integer.parseInt(scanner.next());
                    --tokenIndex;
                    if (tokenIndex > 0 || tokenIndex < board.bound) {
                        tokenExists = true;
                    } else System.out.println("Token-ul cu indexul " + tokenIndex + 1 + " nu exista.");
                } catch (Exception e) {
                    System.out.println(e);
                }
        }

        return tokenIndex;
    }

    @Override
    public void run() {
        while (!board.isGameOver()) {
            ExtractToken(getUserToken());
        }
        terminated = true;
        Thread.currentThread().stop();
        return;
    }
}
