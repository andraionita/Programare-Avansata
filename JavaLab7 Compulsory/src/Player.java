
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * @author Ionita Andra Paula grupa 2A7
 * Laborator 7 Compulsory
 * In clasa player se afla in principiu toata "strategia" jocului.
 * Functia ExtractToken apeleaza functia getToken implementata in board.
 * Dupa fiecare extragere se verifica daca exista printre acei tokeni o progresie de dimensiune data ca input in main.
 * Pentru o mai buna explicatie sa zicem ca progresia are dimensiunea n.
 * Se apeleaza functia checkProgression care in primul rand verifica sa existe mai multi tokeni decat n;
 * Daca este adevarat se apeleaza functia tryProgression care calculeaza existenta unei progresii.
 * Existenta unuia sau mai multor blank carduri aka jokeri 777 poate schimba putin regulile de calcul a progresiei.
 * Daca de exemplu un jucator are un joker, se va cauta o progresie aritmetica de n-1 elemente, daca jucatorul are 2 jokeri
 * se cauta o progresie de n-2 elementr si tot asa. In cazul in care o progresie chiar exista se va afisa pe ecran
 * castigatorul si progresia aritmetica formata din tokeni cu care a castigat. Daca a castigat cu un joker o
 * sa apara si acesta la afisare.
 * In cazul in care nu exista nicio progresie si inca exista tokens, jocul se continua. Daca se termina tokenii dar
 * nimeni nu a obtinut vreo progresie  jocul se opreste si se declara remiza.
 */

public class Player implements Runnable {
    int playerID;
    List<Token> extractedTokens;
    Board board;


    public Player(int playerID, Board board) {
        this.playerID = playerID;
        this.board = board;
        extractedTokens = Collections.synchronizedList(new ArrayList<>());
    }



    private boolean tryProgression(int index){
        int increment = 0;
        boolean primul= true;
        int ok=0;
        for (Token token : extractedTokens) {
            if(token.getValue() == 777)
                ok++;
        }

        int dimProg=Game.getProgressionSize();


        for (int i = index; i < (index + dimProg-ok - 1); i++) {
            if (!extractedTokens.get(i).isJoker()
                    && !extractedTokens.get(i + 1).isJoker()) {
                if (primul) {
                    increment = extractedTokens.get(i + 1).getValue() - extractedTokens.get(i).getValue();
                    primul = false;
                }
                else if ((extractedTokens.get(i + 1).getValue() - extractedTokens.get(i).getValue()) != increment)
                    return false;
            }
        }


        return true;

    }

    public boolean checkProgession() {

        extractedTokens.sort((t1, t2) -> {
            return t1.getValue() - t2.getValue();
        });
        int ok=0;
        for (Token token : extractedTokens) {
            if(token.getValue() == 777)
                ok++;
        }
        if(extractedTokens.size()<Game.getProgressionSize())
            return false;

        for (Token token : extractedTokens) {
            int indexPrincipal = extractedTokens.indexOf(token);

            if(indexPrincipal<= extractedTokens.size()-Game.getProgressionSize()-ok){
                if(tryProgression(indexPrincipal)){
                    if(ok==0) {
                        StringBuilder mesajFinal = new StringBuilder("\n##################################################################################\nPlayer-ul " + playerID + " a castigat cu tokenii : ");
                        for (int i = indexPrincipal; i < (indexPrincipal + Game.getProgressionSize() ); i++) {
                            mesajFinal.append(extractedTokens.get(i) + " ");
                        }

                        System.out.println(mesajFinal.toString()+"\n####################################################################################");
                    }

                    if(ok>0) {
                        StringBuilder stringBuilder = new StringBuilder("\n##################################################################################\nPlayer-ul " + playerID + " a castigat cu tokenii : ");
                        for (int i = indexPrincipal; i < (indexPrincipal + Game.getProgressionSize()-ok ); i++) {
                            stringBuilder.append(extractedTokens.get(i) + ", ");
                        }
                        for(int i=indexPrincipal+Game.getProgressionSize()-ok; i<indexPrincipal+Game.getProgressionSize(); i++)
                            stringBuilder.append("joker: 777" + " ");

                        System.out.println(stringBuilder.toString()+"\n####################################################################################\n");
                    }
                    return true;
                }
            }
        }
        return false;
    }


    public synchronized void ExtractToken() throws InterruptedException {
        Token token = board.getToken();

        System.out.println("Player-ul " + playerID + " a extras " + token);
        extractedTokens.add(token);
        System.out.println("Tokenii extrasi de player " + playerID + ": " + extractedTokens);
        if (checkProgession()) {
            board.available = false;
            notifyAll();
            System.out.println("Player " + playerID + " a avut tokenii: " + extractedTokens);
        }
        if (board.bound == 0) {
            board.available = false;
            notifyAll();
            System.out.println("Nu s-a gasit nicio progresie aritmetica. Remiza");
        }
    }

    @Override
    public void run() {
        board.available = true;
        while (board.available) {
            try {
                ExtractToken();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public String toString() {
        return "Player " + playerID+" ";
    }
}
