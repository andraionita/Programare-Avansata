/**
* @author Ionita Andra, grupa 2A7
* Laborator 10 Compulsory - Server
* In clasa Client Thread este implementata comunicarea cu clientul (in metoda run() mai exact). 
* Sunt tratate mai multe comenzi si situatii (afisarea tablei, conditia de castig, crearea jocului, etc.)
* decat scrie in enunt deoarece am inceput si implementarea partii optionale pe care insa nu am terminat-o. 
* Pentru partea obligatorie trebuiau implementate comanda stop si quit. O sa vedeti implementata
* si comenzile create, join si submit dar momentan cred ca puteti face abstractie de ele.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ClientThread extends Thread {
    private Socket socket = null;
    private final GameServer server;
    private Gomoku joc;
    public int[][] table=new int[19][19];



    //Cream constructoru care primeste ca param serveru si socketul clientului
    ClientThread( Socket socket , GameServer server ) throws SocketException {
        this.socket = socket;
        this.server = server;
        this.joc = null;
        for (int r = 0; r < 19; r++) {
            for (int c = 0; c < 19; c++) {
                table[r][c]=0;
            }

        }
    }

    public void run() {
        BufferedReader fin = null; //client -> server stream
        String request = null;
        String response = null;
        PrintWriter fout = null;
        try {
            fin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            fout = new PrintWriter(socket.getOutputStream()); //server -> client stream
            while(true) {
                request = fin.readLine();
                if( request == null )
                    break;
                response = execute(request);
                fout.println(response);
                fout.flush();
                if( response.equals("exit") ) {
                    break;
                }
            }
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            System.out.println("A expirat timpul!");
            fout.println("exit");

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

    public static void printMatrix(int mat[][])
    {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + "  ");
            System.out.println();
        }
    }

    private String execute(String request) throws IOException {
        System.out.println( request );
        String[] parts = request.split(" ");

        System.out.println("Serverul a primit cererea: " + request );
        switch ( parts[0] ) {
            case "create":
                if( parts.length == 2 ) {
                    if( joc != null )
                        return "Jocul este in progres!";
                    joc = new Gomoku(Integer.parseInt(parts[1]));
                    this.socket.setSoTimeout(3000000); // 3000 sec
                   printMatrix(table);
                 return "Jocul a inceput! ai 3000 secunde la dispozitie sa il termini";
                }
                break;
            case "join":
                if( parts.length == 3 ) {
                    joc = new Gomoku(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                    this.socket.setSoTimeout(3000000); // 3000 sec
                    printMatrix(table);
                    return "Jocul a inceput in 2! ai 3000 secunde la dispozitie sa il termini";
                }
                break;
                case "submit":
                if( joc == null ) {
                    return "Nu ai creat inca jocul!";
                }
                if(castig(this.server.getTable(),joc.getPlayer1())==true) {
                    System.out.println("Jocul s-a termina! A castigat player-ul " + joc.getPlayer1());
                    server.setOver(joc.getPlayer1());
                    return "A castigat player "+joc.getPlayer1()+ " !!";
                }
               else  if(castig(this.server.getTable(),joc.getPlayer2())==true) {
                    System.out.println("Jocul s-a termina! A castigat player-ul "+ joc.getPlayer2());
                    server.setOver(joc.getPlayer2());
                    return "A castigat player "+joc.getPlayer2()+ " !!";
                }

               if( parts.length == 5 ) {

                    table[Integer.parseInt(parts[3])][Integer.parseInt(parts[4])] = Integer.parseInt(parts[1]);

                    this.server.submit(Integer.parseInt(parts[3]),Integer.parseInt(parts[4]),Integer.parseInt(parts[1]));

                    printMatrix(server.getTable());
                    return joc.submit(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[1]));

                }
                break;
            case "quit":
                if( joc != null ) {
                    joc = null;
                    return "Jocul a jost incheiat";
                }
                else {
                    return "First create a game!";
                }
            case "stop":

                socket.close();
                System.out.println("Serverul a primit cererea: " + request );
                return" Serverul a primit cererea: " + request;
        }
        return "Ai o eroare de sintaxa, incearca din nou..";
    }


    public boolean castig(int[][] matrix, int player){

        int cnt=0;
        for(int i=0; i<19; i++)
        {
            for(int j=0; j<19; j++)
            {
                if(table[i][j]==player)
                    cnt++;
                if(cnt!=0 && table[i][j]==0)
                    cnt=0;
                if(cnt>=4)
                    return true;
            }
        }

        for(int j=0; j<19; j++)
        {
            for(int i=0; i<19; i++)
            {
                if(table[i][j]==player)
                    cnt++;
                if(cnt!=0 && table[i][j]==0)
                    cnt=0;
                if(cnt>=4)
                    return true;
            }
        }

        return false;
    }


}
