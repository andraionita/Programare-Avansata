/**
 * @Author: Ionita Andra, grupa A7
 * In clasa SmartPlayer am implementat un jucator cu o stategie in spate.
 * Pentru o explicatie mai buna sa zicem ca dimensiunea progresiei artimetice pentru a castiga este n;
 * Pana la n-1 smart player-ul va alege tokeni random. De la n-1 tokeni incolo va verifica daca cu tokenii 
 * pe care ii are poate forma o progresie de n-1 iar apoi verifica daca in tokenii ramasi exista vreun tokeni
 * cu o valoare care il ajuta sa formeze o progresie de lungime n. In caz adevarat va extrage acel token.
 * Altfel va alege iar token random.
 */
import java.util.Arrays;

import java.util.Random;

public class SmartPlayer extends Player {

    public SmartPlayer(int playerID, Board board) {
        super(playerID, board);
    }

    int findMissingUtil(int arr[], int low, int high, int diff) {


        if (high <= low)
            return Integer.MAX_VALUE;

        int mid = low + (high - low) / 2;

        if (extractedTokens.get(mid + 1).getValue() - extractedTokens.get(mid).getValue() != diff)
            return (extractedTokens.get(mid).getValue() + diff);

        if (mid > 0 && arr[mid] - arr[mid - 1] != diff)
            return (arr[mid - 1] + diff);

        if (arr[mid] == arr[0] + mid * diff)
            return findMissingUtil(arr, mid + 1, high, diff);


        return findMissingUtil(arr, low, mid - 1, diff);
    }


    public int findMissing() {
        int temp = -1;
        while (board.turn != playerID) {
        }
        if (!board.isGameOver()) {
            if (extractedTokens.size() >= 2) {
                int low = 99;
                int high = 0;
                for (Token i : extractedTokens)
                    if (low > i.getValue())
                        low = i.getValue();

                for (Token i : extractedTokens)
                    if (high < i.getValue())
                        high = i.getValue();

                int cnt = 1;
                int[] arr = new int[extractedTokens.size() + 1];
                for (Token i : extractedTokens) {
                    arr[cnt] = (int) i.getValue();
                    cnt++;
                }

                int diff = (high - low) / (arr.length - 1);

                Arrays.sort(arr);


                temp = findMissingUtil(arr, 0, extractedTokens.size() - 1, diff);

                int index = -1;
                boolean ok = false;
                for (Token j : board.tokens) {
                    if (temp == j.getValue()) {
                        index = board.tokens.indexOf(j);
                        --index;
                        ok = true;
                    }
                }
                if (ok == true)
                    return index;
            }


            Random rand = new Random();
            temp = rand.nextInt(board.bound - 1) + 1;
        }
        return temp;

    }

    public void run() {
        while (!board.gameOver) {
            ExtractToken(findMissing());
        }
        terminated = true;
        Thread.currentThread().stop();
        return;
    }
}
