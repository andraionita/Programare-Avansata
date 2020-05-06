
/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Server
 * Clasa pentru Board
 */

package gomoku;


public class Board {
    private int size;
    private int[][] board = new int[19][19];

    public Board(int size) {
        this.size = size;
        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                board[i][j] = 0;
    }

    public int getSize() {
        return size;
    }

    public int[][] getBoard() {
        return board;
    }

}
