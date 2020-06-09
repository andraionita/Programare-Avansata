package server;

import javafx.application.*;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import server.useful.ServerConstants;

/**
 * @author Ionita Andra 2A7
 * Clasa care se ocupa cu manipularea clientului pe threaduri
 */
public class ClientHandler extends Thread {
    public Socket serverSocket;
    ArrayList<ClientHandler> clients;

    DataInputStream dis = null;
    DataOutputStream dos = null;
    private ServerController svc;
    private UserController usc;

    private String clientName = null;
    private String IPAddress = null;
    private int port = 0;

    ArrayList<String> blockedList;
    private HashMap<Integer, Member> members = new HashMap<Integer, Member>();

    //constructor
    public ClientHandler(Socket serverSocket, ArrayList<ClientHandler> clients, ServerController svc, UserController usc) {

        this.serverSocket = serverSocket;
        this.clients = clients;
        this.svc = svc;
        this.usc = usc;
        this.IPAddress = serverSocket.getInetAddress().getHostAddress();
        this.port = serverSocket.getPort();

        svc.addStringConsole("New client connected from IP: " + IPAddress + " and port: " + port, ServerConstants.SYSTEM_MESSAGE);

        try {
            dis = new DataInputStream(serverSocket.getInputStream());
            dos = new DataOutputStream(serverSocket.getOutputStream());
        } catch (IOException e) {
            svc.addStringConsole("Cannot get DataStream ..", ServerConstants.ERROR_MESSAGE);
        }
    }


    public void run() {
        // se conecteaza un nou client
        while (true) {
            int message;
            String strFromClient;

            try {
                message = dis.read();
                strFromClient = dis.readUTF();


                //scriem mesajele in functie de id-ul celui care le-a trimis
                if (message == ServerConstants.BROADCAST_MESSAGE) {
                    //parsam mesajele trimise de user in xml si le scriem in panelu-ul chatului
                    // trimitem la toti inafara de userii blocati

                    String broadcastMessage = getBroadcastMessage(strFromClient);
                    blockedList = getBlockedList(strFromClient);

                    String broadMessageWithUser = clientName + "> " + broadcastMessage;
                    svc.addStringConsole(broadMessageWithUser, ServerConstants.BROADCAST_MESSAGE);
                    for (ClientHandler client : clients) {
                        if (!blockedList.contains(client.clientName)) {
                            client.dos.write(ServerConstants.BROADCAST_MESSAGE);
                            client.dos.writeUTF(broadMessageWithUser);
                        }
                    }

                } else if (message == ServerConstants.LOG_IN) {
                    // un nou user s-a logat

                    clientName = strFromClient.split("\\|")[0];
                    String clientPassword = strFromClient.split("\\|")[1];

                    if (usc.isLoginSuccessful(clientName, clientPassword)) {
                        // Log in cu success
                        if (!isMemberLoggedOn(clientName)) {
                            dos.write(ServerConstants.LOG_IN_SUCCESS);
                            dos.writeUTF(clientName);

                            // afisam notificare ca userul a intrat in chat
                            String welcomeNewMember = clientName + " has joined the chat";
                            svc.addStringConsole(welcomeNewMember, ServerConstants.SYSTEM_MESSAGE);

                            // adaugam noul user la lista de oamnei aflati in chat
                            Member member = new Member(clientName, IPAddress, port, false);
                            members.put(port, member);

                            Platform.runLater(new Runnable() {
                                public void run() {
                                    svc.listObvMember.add(member);
                                }
                            });

                            // atentionam toti clientii ca a venit un nou user
                            for (ClientHandler client : clients) {
                                client.dos.write(ServerConstants.SYSTEM_MESSAGE);
                                client.dos.writeUTF(welcomeNewMember);

                                if (client.clientName.equals(clientName)) {
                                    //trimitem la toti userii informatiile despre noul membru care pot fi vizualizate in tabel

                                    for (ClientHandler clientEach : clients) {
                                        client.dos.write(ServerConstants.NEW_MEMBER);
                                        client.dos.writeUTF(clientEach.clientName + "|" + clientEach.IPAddress + "|" + clientEach.port);
                                    }
                                } else {
                                    //trimitem la noul membru informatiile despre userii deja existenti in chat
                                    // Send only the new member info to the those existing members
                                    client.dos.write(ServerConstants.NEW_MEMBER);
                                    client.dos.writeUTF(clientName + "|" + IPAddress + "|" + port);
                                }
                            }
                        } else {
                            // userul nu se poate loga ptc este deja logat in chat

                            svc.addStringConsole("Username " + clientName + " logged in failed (already logged on)", ServerConstants.ERROR_MESSAGE);
                            dos.write(ServerConstants.LOG_IN_FAIL);
                            dos.writeUTF("Username is already logged in");
                        }
                    } else {
                        // logare esuata la userul nou
                        svc.addStringConsole("Username " + clientName + " logged in failed", ServerConstants.ERROR_MESSAGE);
                        dos.write(ServerConstants.LOG_IN_FAIL);
                        dos.writeUTF("Invalid Username or Password");
                        //break;
                    }


                } else if (message == ServerConstants.LOG_OUT) {
                    //userul a iesit din chat

                    String logOutMessage = clientName + " has left the chat";
                    svc.addStringConsole(logOutMessage, ServerConstants.SYSTEM_MESSAGE);

                    // stergem userul delogat din lista de membri
                    int logOutPort = Integer.parseInt(strFromClient);
                    Member logOutMember = members.get(logOutPort);
                    Platform.runLater(new Runnable() {
                        public void run() {
                            svc.listObvMember.remove(logOutMember);
                        }
                    });
                    members.remove(logOutPort);

                    // trimitem mesaj cum ca s-a delogat la toti ceilalti care sunt in chat
                    for (ClientHandler client : clients) {
                        if (!client.clientName.equals(clientName)) {
                            client.dos.write(ServerConstants.SYSTEM_MESSAGE);
                            client.dos.writeUTF(logOutMessage);

                            client.dos.write(ServerConstants.LOG_OUT);
                            client.dos.writeUTF(strFromClient);
                        }
                    }
                    //nu mai e nevoie de threadul asta deci dam break;
                    break;

                } else if (message == ServerConstants.PRIVATE_MESSAGE) {
                    // trimitem mesaje private

                    String targetUser = strFromClient.split("\\|")[0];
                    String privateMessage = strFromClient.split("\\|")[1];
                    privateMessage = "From " + clientName + " to " + targetUser + "> " + privateMessage;
                    svc.addStringConsole(privateMessage, ServerConstants.PRIVATE_MESSAGE);
                    for (ClientHandler client : clients) {
                        if (client.clientName.equals(targetUser)) {
                            client.dos.write(ServerConstants.PRIVATE_MESSAGE);
                            client.dos.writeUTF(privateMessage);
                        }
                    }
                } else {
                    // Unknown message code
                    System.out.println("Unknown message code.." + strFromClient);
                }
            } catch (IOException e) {
                svc.addStringConsole("Error in communication with the server.", ServerConstants.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }

        try {
            clients.remove(this);
        } catch (Exception e) {
            svc.addStringConsole("Cannot remove client", ServerConstants.ERROR_MESSAGE);
        }
    }

    //verificamm  daca userul este sau nu logat in chatroom
    private boolean isMemberLoggedOn(String username) {
        boolean flag = false;
        for (Member member : svc.listObvMember) {
            if (member.getName().equals(username)) {
                flag = true;
                break;
            }
        }
        return flag;
    }


    //parsam mesajele trimise de user in xml si le scriem in panelu-ul chatului
    // //extragem doar mesajele care vor aparea pentru client
    private String getBroadcastMessage(String xmlMessage) {

        String broadcastMessage = "";
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmldoc = docReader.parse(new InputSource(new StringReader(xmlMessage)));

            Element root = xmldoc.getDocumentElement();
            NodeList certainNodes = root.getElementsByTagName("TEXT");
            for (int i = 0; i < certainNodes.getLength(); i++) {
                broadcastMessage = ((Element) (certainNodes.item(i))).getAttribute("text");
            }
        } catch (Exception e) {

        }
        return broadcastMessage;
    }

    private ArrayList<String> getBlockedList(String xmlMessage) {
        //parsam mesajele trimise de user in xml si le scriem in panelu-ul chatului
        // extragem lista cu persoanele blocate ale userului

        ArrayList<String> blockedList = new ArrayList<String>();
        String name = "";
        try {
            DocumentBuilder docReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document xmldoc = docReader.parse(new InputSource(new StringReader(xmlMessage)));

            Element root = xmldoc.getDocumentElement();
            NodeList certainNodes = root.getElementsByTagName("NAME");
            for (int i = 0; i < certainNodes.getLength(); i++) {
                name = ((Element) (certainNodes.item(i))).getAttribute("name");
                blockedList.add(name);
            }
        } catch (Exception e) {

        }
        return blockedList;
    }


}
