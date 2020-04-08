/**
 * @Author: Ionita Andra, grupa A7
 * Laborator 7 Optional
 * In clasa main am creat un spatiu de testare pentru un joc de 15 secunde, 3 playeri, o 
 * progresie de lungime 3 si 20 tokeni.
 */


import java.util.ArrayList;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //generam 20 tokeni
        ArrayList<Token> tokens = new ArrayList<>();
        var temp = IntStream.rangeClosed(1, 20).mapToObj(i -> new Token(i)).toArray(Token[]::new);
        for (Token t : temp)
            tokens.add(t);
        int progressionSize = 3; //lungimea progresiei
        int players = 3; //numarul de playeri
        int sec=15; //durata unui joc
        Board board = new Board(tokens,progressionSize, players);

        Game game = new Game(players,board,sec);
        game.Start();
    }
}
