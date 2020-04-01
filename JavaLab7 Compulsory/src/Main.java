
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;


/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 7 Compulsory
 * In clasa main am creat array-ul cu tokeni. Precizez ca un token detine un numar, 2 tokeni nu au voie sa fie la fel;
 * Vor exista 3 jokeri, anume tokeni blank pe partida.
 * Ca input de la tastatura se dau numarul de jucatori (macar 2) si marimea progresiei.
 * Precizez ca atunci cand progresia aritmetica dintr-o lista de tokeni atinge dimensiunea data aici ca input jocul ia sfarsit.
 * Tot aici apelam constructorul de la joc si apelam start.
 */
public class Main {
    public static void main(String[] args) {
        //generam un array de 50 tokeni.
        ArrayList<Token> tokens = new ArrayList<>();

        var temp = IntStream.rangeClosed(1, 50).mapToObj(i -> new Token(i)).toArray(Token[]::new);


        for (Token t : temp)
            tokens.add(t);


        ///////////Per partida vor exista doar 3 jokeri, anume acele carti blank care ofera posibilitatea celui care
        ///////////a extras-o sa o inlocuiasca cu orice alt token posibil.
        Token joker1=new Token(777);
        Token joker2=new Token(777);
        Token joker3=new Token(777);
        tokens.add(joker1);
        tokens.add(joker2);
        tokens.add(joker3);

        Board board = new Board(tokens);

        Scanner citim = new Scanner(System.in);

        System.out.println("Cati playeri joaca?");
        int players = citim.nextInt();  // Read user input
        System.out.println("Cat de mare sa fie dimensiunea progresiei:");
        int progressionSize = citim.nextInt();  // Read user input
        Game game = new Game(progressionSize,players,board);
        game.Start();
    }
}
