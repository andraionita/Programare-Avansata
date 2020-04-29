
/**
* @author Ionita Andra, grupa 2A7
* Laborator 10 Compulsory - Server
* In clasa Clasa Gomoku este implementat jocu si metode si el. Nu este insa necesara pentru partea obligatorie.
*/
import java.util.Arrays;

public class Gomoku {
    private int player;

    private int attempts;
    public int[][] table=new int[19][19];
    public int player1, player2;


    Gomoku(int player) {
        this.player = player;
        attempts = 0;
        for (int r = 0; r < 19; r++) {
            for (int c = 0; c < 19; c++) {
                table[r][c] = 0;
            }

        }
    }

    Gomoku(int player1, int player2) {
       this.player1=player1;
        this.player2 = player2;
        attempts = 0;
        for (int r = 0; r < 19; r++) {
            for (int c = 0; c < 19; c++) {
                table[r][c] = 0;
            }

        }
    }


    public int getPlayer() {
        return player;
    }

    public int getPlayer1() {
        return player1;
    }

    public int getPlayer2() {
        return player2;
    }

    public int getAttempts() {
        return attempts;
    }

    public int[][] getTable() {
        return table;
    }


    public String submit( int r, int c, int player ) {
        attempts++;
        table[r][c]=player;
        return  "Pe linia "+r+" coloana "+c+" am pus \"bila\" ta cu "+player;
    }

    @Override
    public String toString() {
        return "Gomoku{" +
                "table=" + Arrays.toString(table) +
                '}';
    }


}
