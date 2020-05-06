/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Client
 * Clientul va citi introducerea jocului si apoi va schimba informatii cu serverul.
 */
import java.io.PrintWriter;
import java.util.Scanner;
import java.net.Socket;
import java.io.IOException;


public class GameClient {
    private static final String ip = "127.0.0.1";
    private static final int port = 80001;
    private static String command;

    private Socket socket;
    private Scanner in;
    private Scanner scanner;
    private PrintWriter out;
    private Board board;
    private int round;
    private int myIndex;
    private String response;

    public GameClient() {
        try {
            board = new Board(19);
            socket = new Socket(ip, port);
            scanner = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new Scanner(socket.getInputStream());
            String response = in.nextLine();
            System.out.println(response);

            while (!response.startsWith("Bun venit in joc!")) {
                command = scanner.nextLine();
                out.println(command);
                response = in.nextLine();
                System.out.println(response);

            }

            if (response.toUpperCase().startsWith("Bun venit in joc!")) {
                myIndex = Integer.parseInt(response.substring(8));

                response = in.nextLine();
                System.out.println(response);

                response = in.nextLine();
                System.out.println(response);

                //pentru playerul 2
                if (response.startsWith("Adversarul")) {
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

                play();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void play() {
        ReadingFromSocket read = new ReadingFromSocket(in, board, myIndex);
        WritingInSocket write = new WritingInSocket(scanner, out, board, read);
        new Thread(read).start();
        new Thread(write).start();
        return;
    }
}