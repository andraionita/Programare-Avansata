import java.io.IOException;
import java.util.Scanner;
/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Client
 * Clasa care va citi din socket
 */
public class ReadingFromSocket implements Runnable {
    private Scanner in;
    private String response;
    private Board board;
    private int myIndex;
    private volatile String command = "init";

    public ReadingFromSocket(Scanner scanner, Board board, int myIndex) {
        this.in = scanner;
        this.board = board;
        this.myIndex = myIndex;
    }

    public synchronized void setCommand(String command) {
        this.command = command;
    }

    @Override
    public void run() {
        while (in.hasNextLine()) {
            response = in.nextLine();
            System.out.println("Raspuns: " + response);

            if (!response.startsWith("Exception") && !response.startsWith("Comanda necunoscuta")) {
                String[] pieces = command.split(" ");
                int col = Integer.parseInt(pieces[1]);
                int row = Integer.parseInt(pieces[2]);
                board.setCell(row, col, myIndex);
            }

            if (response.startsWith("Adverasul si-a pus \"bila\"")) {
                String[] pieces = response.split(" ");
                int col = Integer.parseInt(pieces[4]);
                int row = Integer.parseInt(pieces[5]);
                board.setCell(row, col, 3 - myIndex);

                for (int i = 0; i < 19; ++i) {
                    for (int j = 0; j < 19; ++j)
                        System.out.print(board.getBoard()[i][j] + " ");
                    System.out.println();
                }
            }


            if (response.endsWith("VICTORIE!") || response.endsWith("INFRANGEREA!"))
                break;

            if (response.equals("Oponentul a parasit jocul."))
                break;
        }
        System.exit(0);
    }
}

