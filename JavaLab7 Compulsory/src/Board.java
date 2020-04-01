

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 7 Compulsory
 * In clasa Board avem lista de tokeni creata in main. Bound-ul este de 50+3 deoarece in main am
 * "hotarat" sa existe 50 tokeni iar +3 reprezinta existenta celor 3 jokeri printre tokeni.
 * O alta functie importanta asta este cea de getToken care genereaza un index random pana la bound si alege token-ul existent
 * la acel bound. Dupa fiecare alegere token-ul se decrementeaza. Atunci cand se apeleaza functia getToken, available va trece pe false
 * lucru care da anunta ceilalti playeri ca trebuie sa astepte pana sa aleaga un token. Dupa ce se finalizeaza alegerea unui token
 * available va trece pe true iar ceilalti player vor putea alege si ei in ordine un token.
 */

public class Board {
    ArrayList<Token> tokens;
    public boolean available = false;
    public int bound = 50+3;

    public Board(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public synchronized Token getToken() {

        available = false;
        notifyAll();
        Random r = new Random();
        int randomToken = r.nextInt(bound);
        Token chosen = tokens.get(randomToken);
        tokens.remove(randomToken);
        if (bound > 0)
            bound--;
        available = true;
        notifyAll();
        return chosen;
    }


}
