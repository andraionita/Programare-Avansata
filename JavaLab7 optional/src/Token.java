/**
 * @author: Ionita Andra, grupa A7
 * Laborator 7 Optional - aceasi clasa ca cea de la Compulsory
 */


public class Token {
    public boolean blank=false;
    int value;

    public boolean isBlank() {
        return blank;
    }

    public Token(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "token: " + value;
    }
}