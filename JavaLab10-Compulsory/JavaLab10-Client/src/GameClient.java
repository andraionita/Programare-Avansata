/**
* @author Ionita Andra, grupa 2A7
* Laboratorul 10 Compulsory - Client
* In aceasta clasa ne conectam la server cu port-ul si adresa. Apoi putem trimitem la server prin socket anumite comenzi date de la
* tastura (join, create, submit move, quit sau stop), si citim si afisam raspunsu primit de la server.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class GameClient {
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int PORT = 8100;
    private static PrintWriter fout = null ;
    private static BufferedReader fin = null ;


    public static void main(String[] args) {
        int[][] table=new int[19][19];

        for (int r = 0; r < 19; r++) {
            for (int c = 0; c < 19; c++) {
                table[r][c]=0;
            }

        }
        Socket socket = null;
        GameClient client = new GameClient();
        int i=0, j=0, value=0;
        System.out.println("#######################################################" +
                "\n\t\t\t\tEsti pe server!" +
                "\n#######################################################" +
                "\nScrie o comanda dintre urmatoarele:"
                +"\n\t\t\t1) create [int idPlayer] "+"\n\t\t\t2)  submit [int idPlayer] move [int row] [int column] " +"\n\t\t\t3) join [int idPlayer1] [int idPlayer2]"+"\n\t\t\t4) stop"+"\n\t\t\t5) exit");
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            fin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            fout = new PrintWriter(socket.getOutputStream());
            while (true) {
                String request = client.readFromKeyboard();
                String[] parts = request.split(" ");
                if(parts[0]=="create"){
                    printMatrix(table);
                }


                if (request.equalsIgnoreCase("quit")) {
                    fin.close();
                    fout.close();
                    break;
                }
                else if(request.equalsIgnoreCase("stop")){
                    socket.close();
                    System.out.println("Serverul s-a oprit");
                }
                else
                 {

                    switch ( parts[0] ) {
                        case "create":
                            printMatrix(table);
                            value = Integer.parseInt(parts[1]);
                            break;

                        case "join":
                            printMatrix(table);
                            value = Integer.parseInt(parts[2]);
                            break;

                        case "submit":
                            i = Integer.parseInt(parts[3]);
                            j = Integer.parseInt(parts[4]);

                            table[i][j] = value;
                            printMatrix(table);
                            break;

                    }
                    client.sendRequestToServer(request);

                }
            }
            System.out.println("terminat");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendRequestToServer(String request) throws IOException {
        try {
            fout.println(request);
            fout.flush();
            request = fin.readLine();
            System.out.println(request);
            if(request=="Ai castigat!!") {
                fin.close();
                fout.close();
            }
        } catch (SocketException e) {
            System.out.println("A expirat timpul de joc.");
            fin.close();
            fout.close();
        }
    }

    private String readFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    public static void printMatrix(int mat[][])
    {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + "  ");
            System.out.println();
        }
    }
}
