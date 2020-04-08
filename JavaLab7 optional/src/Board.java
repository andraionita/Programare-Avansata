/**
 * @Author: Ionita Andra, grupa A7
 * Laboratorul 7 Optional
 * Am schimbat implementarea acestei clase. Am implementat un semafor pentru 
 * extragerea unui token pentru playeri.
 * In rest sunt contructor, getter si setter pentru clasa.
 */


import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class Board {
    ArrayList<Token> tokens;
    public boolean available = false;
    public int bound = 20;
    int progressionSize;
    public volatile boolean gameOver = false;
    public int round = 0;
    public int nrPlayers;
    public volatile int turn = 1;

    
    public Board(ArrayList<Token> tokens, int size, int playersNumber) {
        this.tokens = tokens;
        this.progressionSize = size;
        this.nrPlayers = playersNumber;
    }

    //Actualizam functia in functie de tipul de player ca fiecare sa extraga token in functie de stategie
    public synchronized Token getToken(Player player, int tokenIndex) {
        Token chosen = new Token(0);
        while (turn != player.playerID) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!gameOver && bound > 0) {
            chosen = tokens.get(tokenIndex);
            player.extractedTokens.add(chosen);
            tokens.remove(tokenIndex);
            bound--;
            System.out.println("Player " + player.playerID + " a extras token-ul cu valoarea " + chosen.value);
            System.out.println("");
            if (player.hasProgression()) {
                System.out.println("Player " + player.playerID + " a castigat!!!");
                System.out.println("Combinatia cu care a castigat: " + player.extractedTokens);
                gameOver = true;
            }
        }
        if (bound == 0)
            gameOver = true;
        ++round;
        turn = round % nrPlayers +1;
        notifyAll();
        return chosen;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getNrPlayers() {
        return nrPlayers;
    }

    public void setNrPlayers(int nrPlayers) {
        this.nrPlayers = nrPlayers;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
