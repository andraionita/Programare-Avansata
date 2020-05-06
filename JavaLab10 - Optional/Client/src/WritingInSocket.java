import java.io.PrintWriter;
import java.util.Scanner;
/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Client
 * Clientul va scrie in socket
 */
public class WritingInSocket implements Runnable {
    public Scanner scanner;
    private PrintWriter out;
    private volatile String command;
    private Board board;
    private ReadingFromSocket readingFromSocket;

    public WritingInSocket(Scanner scanner, PrintWriter printWriter, Board board, ReadingFromSocket readingFromSocket) {
        this.scanner = scanner;
        this.out = printWriter;
        this.board = board;
        this.readingFromSocket = readingFromSocket;
    }

    @Override
    public void run() {
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            System.out.println("Comanda: " + command);
            out.println(command);

            readingFromSocket.setCommand(command);

            if (command.toLowerCase().equals("stop"))
                break;
        }
    }
}
