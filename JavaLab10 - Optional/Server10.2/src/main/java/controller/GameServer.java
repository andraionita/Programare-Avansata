package controller;

/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Server
 * Clasa singletone care creaza un ThreadPool pentru a suporta mai multe jocuri in 2 de Gomoku in paralel.
 * Un nou joc e creat atunci cand 2 playeri se alatura jocului.
 * Un joc este share-uit de 2 playeri.
 */

import gomoku.Board;
import gomoku.Game;
import gomoku.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

public class GameServer {

    private static GameServer ourInstance = new GameServer();
    private PrintWriter out;
    private InputStreamReader in;
    private String command;
    private static final int port = 80001;

    public static GameServer getInstance() {
        return ourInstance;
    }

    private GameServer() {
        try (var listener = new ServerSocket(port)) {
            System.out.println("Jocul ruleaza...");
            var pool = Executors.newFixedThreadPool(200); //folosim un threadpool si un executor service pentru gesiunea threadurilor
            while (true) {
                Board board = new Board(25);
                Game gomoku = new Game(board);
                pool.execute(new ClientThread(listener.accept(), new Player(1, gomoku)));
                pool.execute(new ClientThread(listener.accept(), new Player(2, gomoku)));
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}