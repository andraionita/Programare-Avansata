/**
* @author Ionita Andra, grupa 2A7
* Laborator 10 Compulsory - Server
* In clasa Game Server am impementat Server-ul. In primul rand cream socket-ul in init() apoi cu un
* while infinit acceptam cererile venite de la clienti pentru a intra pe server si cream pentru fiecare client
* un thread si un socket.
*/
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class GameServer {
    private static final int PORT = 8100;
    private ServerSocket serverSocket;
    private boolean running = false;
    public int[][] table=new int[19][19];
    public int gameOver;

    public void submit(int i, int j, int player)
    {
        table[i][j]=player;
    }
    public static void main(String[] args)  {

        GameServer server = new GameServer();
        server.setOver(-1);
        server.init();
        server.waitForClients(); //... handle the exceptions!
        if(server.getOver()!=-1)
        {
            System.out.println("Partida s-a terminat!");
        }

    }
    // Implement the init() method: cream serverSocket si il punem pe true
    private void init() {
        for (int r = 0; r < 19; r++) {
            for (int c = 0; c < 19; c++) {
                table[r][c] = 0;
            }
        }
        try {
            serverSocket = new ServerSocket( PORT );
        } catch (IOException e) {
            e.printStackTrace();
        }
        running = true;
    }


    //implementam waitForClients(): cat timp running e true se creaza un nou socket pt fiecare client si incepe sa se execute ClientThread
    private void waitForClients(){
        while( running ) {
            System.out.println(" Asteptam un client... ");
            try {

                Socket socket = serverSocket.accept();
                new ClientThread(socket,this).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int[][] getTable() {
        return table;
    }

    public int getOver()
    {
        return gameOver;
    }
    public void setOver(int ok)
    {
        this.gameOver=ok;
    }

    public void stop() throws IOException {
        this.running = false;
        serverSocket.close();
    }

    private String readFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
