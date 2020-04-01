
import java.util.Random;

/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 7 Compulsory
 * In clasa Token exista doar constructor, getter, setter si functia toString.
 */


public class Token {
    private static boolean Joker=false;
    int value;


    public boolean isJoker() {
        return Joker;
    }

    public static void setJoker(boolean temp){
        Joker=temp;

    }

    public Token(int value) {

        this.value = value;

    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if(value==777) return "joker " + value;
        else
            return "token: " + value;
    }
}
