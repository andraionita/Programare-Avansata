/**
 * @Author: Ionita Andra, grupa A7
 * Laboratorul 7 Optional
 * Clasa Player este in principiu identica cu cea de la laboratorul Compulsory cu exceptia unei functii
 * si anume functia getLargestProgression care calculeaza cea mai mare progresie care se poate forma 
 * din tokenii actuali. De asemmenea este o diferenta faptul ca clasa a devenit abstracta.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

public abstract class Player implements Runnable {
    int playerID;
    List<Token> extractedTokens;
    Board board;
    protected volatile boolean terminated = false;

    public Player(int playerID, Board board) {
        this.playerID = playerID;
        this.board = board;
        extractedTokens = Collections.synchronizedList(new ArrayList<>());
    }


    private synchronized void printProgression(int index) {
        StringBuilder stringBuilder = new StringBuilder("S-a gasit porgresie pentru player-ul " + playerID + " cu numerele: ");
        for (int i = index; i < (index + board.progressionSize); i++) {
            stringBuilder.append(extractedTokens.get(i));
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

    private boolean isProgression(int index) {
        int lastIncrement = 0;
        boolean isFirstIncrement = true;
        for (int i = index; i < (index + board.progressionSize - 1); i++) {
            if (!extractedTokens.get(i).blank
                    && !extractedTokens.get(i + 1).blank) {
                if (isFirstIncrement) {
                    lastIncrement = extractedTokens.get(i + 1).getValue() - extractedTokens.get(i).getValue();
                    isFirstIncrement = false;
                } else if ((extractedTokens.get(i + 1).getValue() - extractedTokens.get(i).getValue()) != lastIncrement)
                    return false;
            }
        }
        return true;
    }

    public synchronized boolean hasProgression() {
           extractedTokens.sort((t1, t2) -> {
            return t1.getValue() - t2.getValue();
        });
        if (extractedTokens.size() < board.progressionSize)
            return false;
        for (Token token : extractedTokens) {
            int firstElementIndex = extractedTokens.indexOf(token);
            if (firstElementIndex <= extractedTokens.size() - board.progressionSize) {
                if (isProgression(firstElementIndex)) {
                    printProgression(firstElementIndex);
                    board.gameOver=true;
                    return true;
                }
            }
        }
        return false;
    }

    public int getLargestProgression() {
        int n = extractedTokens.size();
        int L[][] = new int[n][n];
        int llap = 2;
        for (int i = 0; i < n; i++)
            L[i][n - 1] = 2;
        // Consideram fiecare element ca al doilea al unei progresii aritmetice
        for (int j = n - 2; j >= 1; j--) {
            // Cautam for i and k for j
            int i = j - 1, k = j + 1;
            while (i >= 0 && k <= n - 1) {
                if (extractedTokens.get(i).value + extractedTokens.get(k).value < 2 * extractedTokens.get(j).value)
                    k++;
                    // Iniante sa schimbam  i, setam L[i][j] ca 2
                else if (extractedTokens.get(i).value + extractedTokens.get(k).value > 2 * extractedTokens.get(j).value) {
                    L[i][j] = 2;
                    i--;
                } else {
                    L[i][j] = L[j][k] + 1;
                    llap = Math.max(llap, L[i][j]);
                    i--;
                    k++;
                }
            }
            while (i >= 0) {
                L[i][j] = 2;
                i--;
            }
        }
        return llap;
    }

    public synchronized void ExtractToken(int tokenIndex) {
        if (!board.gameOver && board.turn==this.playerID) {
            Token token = board.getToken(this, tokenIndex);
        }
        else terminated=true;
    }

    @Override
    public void run() {
        while (!terminated) {
            ExtractToken(0);
        }
        Thread.currentThread().stop();
        return;
    }

    @Override
    public String toString() {
        return "Player " + playerID;
    }
}
