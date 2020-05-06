package controller;

/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Server
 * Clasa care va rula thread-ul specific pentru un player.
 */


import gomoku.Player;

import java.io.*;
import java.net.Socket;


public class ClientThread implements Runnable {
    private Socket socket;
    private Player player;
    private String command;

    public ClientThread(Socket socket, Player player) {
        this.socket = socket;
        this.player = player;
        this.player.setSocket(socket);
    }

    @Override
    public void run() {
        System.out.println("Conectat: " + socket);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Esti in joc!!\nAlege una dintre comenzile:\n\t\t\t ---start \t\t\t---move \t\t\t---stop");
            command = "init";

            while (!command.equals("start")) {
                command = in.readLine();
                System.out.println("A primit comanda" + command);
                if (command.toLowerCase().equals("start")) {
                    new Thread(player).start();
                } else if (command.toLowerCase().equals("stop")) {
                    out.println("Jocul nu este creat pentru a fi oprit");
                    System.out.println("Clientul vrea sa opreasca serverul");
                    //socket.close();
                    //break;
                } else {
                    out.println("Eroare de sintaxa, incearca din nou");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
