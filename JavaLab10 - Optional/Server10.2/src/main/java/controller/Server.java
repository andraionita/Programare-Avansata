package controller;

import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
/**
 * @author: Ionita Andra Paula, 2A7
 * Laborator 10 Optional -> Server
 * Implementare FTP
 *
 */
public class Server {

    private String host;
    private Integer port;
    private String user;


    private JSch jsch;
    private Session session;
    private Channel channel;
    private ChannelSftp sftpChannel;

    public Server(String host, Integer port, String user) {
        this.host = host;
        this.port = port;
        this.user = user;
    }

    public void connect() {

        System.out.println("Conectam..." + host);
        try {
            jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;

            System.out.println("Connected");

        } catch (JSchException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        System.out.println("Deconectare...");
        sftpChannel.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    public void upload(String fileName, String remoteDir) {
        System.out.println("Incarcam...");

        FileInputStream fis = null;
        connect();
        try {
            // Schimbam directorul de output
            sftpChannel.cd(remoteDir);

            // Incarcam fisiere
            File file = new File(fileName);
            fis = new FileInputStream(file);
            sftpChannel.put(fis, file.getName());

            fis.close();
            System.out.println("Fisierul a fost incarcat cu succes - " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

}