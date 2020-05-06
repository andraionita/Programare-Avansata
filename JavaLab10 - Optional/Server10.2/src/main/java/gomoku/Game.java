
/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Server
 * Clasa Game va verifica conditiile de castig, va verifica conditiile pentru mutare (daca este posibila)
 */


package gomoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import controller.*;


public class Game {
    private Player currentPlayer;
    private Board board;
    public Report report;
    public ArrayList<Move> allMoves;

    public Game(Board board) {
        this.board = board;
        allMoves = new ArrayList<>();
        report = new Report();
        try {
            report.configure();
            report.processTemplate();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public boolean isBoardFull() {
        return Arrays.stream(board.getBoard()).allMatch(p -> p != null);
    }

    public boolean hasWinner() {
        for (int i = 0; i < board.getSize(); ++i) {
            int count = 0;
            for (int j = 1; j < board.getSize(); ++j) {
                if (board.getBoard()[i][j] == board.getBoard()[i][j - 1] &&
                        (board.getBoard()[i][j] == 1 || board.getBoard()[i][j] == 2))
                    ++count;
                else
                    count = 0;
                if (count == 4) //la 5 "bile" la fel consecutive se va considera castig
                    return true;
            }
            count = 0;
        }

        for (int j = 0; j < board.getSize(); ++j) {
            int count = 0;
            for (int i = 1; i < board.getSize(); ++i) {
                if (board.getBoard()[i][j] == board.getBoard()[i - 1][j] &&
                        (board.getBoard()[i][j] == 1 || board.getBoard()[i][j] == 2))
                    ++count;
                else
                    count = 0;
                if (count == 4) //la 5 "bile" la fel consecutive se va considera castig
                    return true;
            }
            count = 0;
        }
        return false;
    }

    public synchronized void move(int row, int col, Player player) {
        if (!player.equals(currentPlayer)) {
            throw new IllegalStateException("Nu este randul tau! Astepta!");
        } else if (player.opponent == null) {
            throw new IllegalStateException("Trebuie sa astepti inca un player pentru a putea juca.");
        } else if (board.getBoard()[col][row] != 0) {
            throw new IllegalStateException("Pozitie din tabla deja ocupata! Alege alta!");
        }
        Move move = new Move(row, col, currentPlayer.getMark());
        allMoves.add(move);
        board.getBoard()[col][row] = currentPlayer.getMark();
        currentPlayer = currentPlayer.opponent;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
