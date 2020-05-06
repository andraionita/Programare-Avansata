
/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Server
 * Clasa Player.
 * Atunci cand 2 playeri se alatura jocului serverul incepe comunicarea dintre ei.
 * volatile opponent este semaforul care anunta playerul cand este randul lui sa mute/sa comunice cu serverul.
 */

package gomoku;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import controller.Server;

public class Player implements Runnable {
    public int mark;
    public volatile Player opponent;
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private Game game;

    public Player(int mark, Game game) {
        this.mark = mark;
        this.game = game;
    }

    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("Bun venit in joc " + mark);
        if (mark == 1) {
            game.setCurrentPlayer(this);
            output.println("Asteapta un oponent");
            System.out.println("Se asteapta un al doilea player pentru a putea incepe jocul de Gomoku");
        } else {
            opponent = game.getCurrentPlayer();
            opponent.opponent = this;
            output.println("Asteapta sa mute oponentul.");
            opponent.output.println("Este randul tau");
            System.out.println("Jocul a inceput");
        }
    }

    private void processCommands() {
        System.out.println("Processing commands...");
        while (input.hasNextLine()) {
            String command = input.nextLine();
            System.out.println("Got command from player " + this.mark + ": " + command);
            if (command.toLowerCase().startsWith("move")) {
                String[] parts = command.split(" ");
                int row = Integer.parseInt(parts[1]);
                int col = Integer.parseInt(parts[2]);
                processMoveCommand(col, row);
            } else if (command.toLowerCase().equals("stop")) {
                System.out.println("Player-ul " + mark + " a parasit jocul");
                try {
                    //Cream raportul
                    game.report.putMoves(game.allMoves);
                    game.report.flushAll();
                    System.out.println("Am creat raportul jocului");

                    socket.close(); //close the socket

                    String localPath = "E:/DESKTOP/JavaLab10/Raport/";
                    String remotePath = ".";

                    Server ftp = new Server("127.0.0.1", 80, "root");

                    ftp.upload(localPath + "Gomoku.html", remotePath);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                System.out.println("Joc terminat");
                break;
            } else {
                System.out.println("Comanda necunoscuta");
                output.println("Comanda necunoscuta. Incearca din nou sau vezi problemele de sintaxa");
            }
        }
    }

    private void processMoveCommand(int column, int row) {
        try {
            game.move(column, row, this);
            for (int i = 0; i < 19; ++i) {
                for (int j = 0; j < 19; ++j)
                    System.out.print(game.getBoard().getBoard()[i][j] + " ");
                System.out.println();
            }
            if (game.hasWinner()) {
                output.println("Mutarea " + row + " " + column + " - a provocat VICTORIE!");
                opponent.output.println("Adversarul a pus \"bila\" pe pozitia " + row + " " + column + " - a provocat INFRANGEREA!");
                game.report.putMoves(game.allMoves);
                game.report.putWinner(this);
                game.report.flushAll();

                String localPath = "E:/DESKTOP/JavaLab10/Raport/";
                String remotePath = ".";

                Server ftp = new Server("127.0.0.1", 80, "root");

                ftp.upload(localPath + "Gomoku.html", remotePath);

                System.out.println("Joc terminat! A fost creat raportul jocului.");
            } else {
                opponent.output.println("Adverasul a pus \"bila\" pe pozitia " + row + " " + column);
            }

        } catch (IllegalStateException e) {
            output.println("Exception" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            setup();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (opponent != null && opponent.output != null) {
                opponent.output.println("Oponentul a parasit jocul.");
                try {
                    opponent.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return mark == player.mark;
    }

}
