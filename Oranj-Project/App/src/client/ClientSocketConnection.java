package client;

import client.useful.ServerConstants;
import javafx.application.*;
import server.security.Encrypt;

import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author Ionita Andra 2A7
 * Controllerul pentru Socket
 */
public class ClientSocketConnection {

    private ClientController clc;
    private ClientLoginController cllc;
    public SocketThread socketThread = new SocketThread();
    public Socket client;
    private int port = 5000;

    HashMap<Integer, User> members = new HashMap<Integer, User>();

    public ClientSocketConnection() {

    }

    public ClientSocketConnection(ClientController clc, ClientLoginController cllc) {
        this.clc = clc;
        this.cllc = cllc;
    }

    ///setam portul
    public void setPort(int port) {
        this.port = port;
    }

    public void refreshThread() {
        socketThread = new SocketThread();
    }

    public void startClient() throws Exception {
        if (!isStart())
            socketThread.run();
    }

    //metoda apelata cand se apasa butonul de exit
    public void stopClient() throws IOException {

        if (isStart()) {
            sendMessage(ServerConstants.LOG_OUT, String.valueOf(client.getLocalPort()));
        }
        stopClientSocket();
    }


    // opreste toate casetele de input cand nu exista conexiune
    public void stopClientSocket() {

        clc.txtSend.setDisable(true);
        clc.btnSend.setDisable(true);
        clc.btnSendFile.setDisable(true);
    }

    public boolean isStart() {
        return client != null;
    }


    //creaza mesajele ca un xml
    public String createXMLMessage(String message) {
        String xmlMessage = "<MESSAGE>\n";
        xmlMessage += "	<TEXT text ='" + message + "'/>\n";
        xmlMessage += "	<BLOCK>\n";
        for (User eachUser : members.values()) {
            if (eachUser.getName().equals(clc.txtName.getText()))
                continue;
            if (eachUser.getIsBlocked())
                xmlMessage += "		<NAME name ='" + eachUser.getName() + "'/>\n";
        }
        xmlMessage += "	</BLOCK>\n";
        xmlMessage += "</MESSAGE>\n";
        return xmlMessage;
    }

    //trimite mesajul
    public void sendMessage(String textMessage) throws IOException {
        sendMessage(ServerConstants.BROADCAST_MESSAGE, createXMLMessage(textMessage));
    }

    public void sendMessage(int message, String textMessage) throws IOException {
        socketThread.dos.write(message);
        socketThread.dos.writeUTF(textMessage);
    }

    //functie pentru validarea numelui si parolei
    public void doValidateUserNamePassword(String username, String password) throws IOException, NoSuchAlgorithmException {
        sendMessage(ServerConstants.LOG_IN, username + "|" + Encrypt.encrypt(password));
    }


    private class SocketThread extends Thread {
        DataOutputStream dos;
        DataInputStream dis;

        public void run() {
            try {
                client = new Socket("localhost", port);
                clc.addStringConsole("Chat Room starts on port: " + port, ServerConstants.SYSTEM_MESSAGE);

                dos = new DataOutputStream(client.getOutputStream());
                dis = new DataInputStream(client.getInputStream());

                Thread serverHandler = new Thread() {
                    public void run() {
                        while (true) {
                            int message;
                            String textMessage;
                            try {
                                message = dis.read();
                                textMessage = dis.readUTF();

                                if (message == ServerConstants.NEW_MEMBER) {
                                    List<String> listStr = new ArrayList<String>(Arrays.asList(textMessage.split("\\|")));
                                    User user = new User(listStr.get(0), listStr.get(1), Integer.parseInt(listStr.get(2)), false);
                                    members.put(Integer.parseInt(listStr.get(2)), user);
                                    //adaugam nou user la lista
                                    Platform.runLater(new Runnable() {
                                        public void run() {
                                            clc.listObvUser.add(user);
                                            clc.listObvMemberName.add(listStr.get(0));
                                        }
                                    });

                                } else if (message == ServerConstants.LOG_IN_SUCCESS) {
                                    // login cu succes, afisam ecranul de chat
                                    Platform.runLater(new Runnable() {
                                        public void run() {
                                            try {
                                                clc.txtName.setText(textMessage);
                                                cllc.showChatScene(textMessage);
                                            } catch (IOException e) {
                                                // TODO Auto-generated catch
                                                // block
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                } else if (message == ServerConstants.LOG_IN_FAIL) {
                                    // daca usernameul sau parola sunt incorecte
                                    Platform.runLater(new Runnable() {
                                        public void run() {
                                            try {
                                                cllc.txtMessage.setText(textMessage);
                                                cllc.clearAndFocusText();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                } else if (message == ServerConstants.LOG_OUT) {
                                    // daca un prieten s-a delogat anuntam asta si il scoatem din lista de prieteni
                                    int logOutPort = Integer.parseInt(textMessage);
                                    User logOutUser = members.get(logOutPort);
                                    Platform.runLater(new Runnable() {
                                        public void run() {
                                            clc.listObvUser.remove(logOutUser);
                                            clc.listObvMemberName.remove(logOutUser.getName());
                                        }
                                    });
                                    members.remove(logOutPort);
                                    ;

                                } else if (message == ServerConstants.SERVER_STOP) {
                                    //daca serverul s-a oprit stergem lista de prieten si dezactivam toate zonele care pot primi input
                                    clc.addStringConsole("The server stops working", ServerConstants.ERROR_MESSAGE);
                                    Platform.runLater(new Runnable() {
                                        public void run() {
                                            clc.listObvUser.clear();
                                            clc.listObvMemberName.clear();
                                        }
                                    });
                                    stopClientSocket();

                                } else if (message == ServerConstants.BROADCAST_MESSAGE) {
                                    //daca senderul care a trimis mesajul e in block list nu afisam mesajul

                                    List<String> listStr = new ArrayList<String>(Arrays.asList(textMessage.split(">")));
                                    String sender = listStr.get(0);
                                    boolean flag = true;
                                    for (User member : members.values()) {
                                        if (member.getName().equals(sender) && member.getIsBlocked()) {
                                            flag = false;
                                        }
                                    }
                                    if (flag)
                                        clc.addStringConsole(textMessage, ServerConstants.BROADCAST_MESSAGE);

                                } else if (message == ServerConstants.SYSTEM_MESSAGE) {

                                    clc.addStringConsole(textMessage, ServerConstants.SYSTEM_MESSAGE);

                                } else if (message == ServerConstants.PRIVATE_MESSAGE) {

                                    clc.addStringConsole(textMessage, ServerConstants.PRIVATE_MESSAGE);

                                } else {

                                    clc.addStringConsole(textMessage + " with code " + message, ServerConstants.SYSTEM_MESSAGE);

                                }

                            } catch (IOException e) {
                                System.err.println(e);
                                break;
                            }
                        }
                    }
                };

                serverHandler.start();
            } catch (IOException e) {
                clc.addStringConsole("Cannot connect to the server: Code 3", ServerConstants.ERROR_MESSAGE);
                clc.btnSend.setDisable(true);
                clc.txtSend.setDisable(true);
                clc.btnSendFile.setDisable(true);
            }
        }
    }
}
