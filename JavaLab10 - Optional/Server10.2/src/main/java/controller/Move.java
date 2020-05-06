package controller;
/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Server
 * Clasa pentru mutare
 */

public class Move {
    public int col;
    public int row;
    public int playerIndex;

    public Move(int row, int col, int playerIndex) {
        this.col = col;
        this.row = row;
        this.playerIndex = playerIndex;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
}
