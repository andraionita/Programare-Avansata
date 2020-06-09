package server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.fxml.*;
import server.useful.ServerConstants;

import java.io.*;

/**
 * @author Ionita Andra 2A7
 * Clasa serverului
 */
public class Server extends Application {
    ServerSocketConnection ssc;

    //incarcam interfata serverului
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("interfaces/Server.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ServerController svc = fxmlLoader.<ServerController>getController();


            FXMLLoader fxmlLoaderUser = new FXMLLoader(getClass().getResource("interfaces/Users.fxml"));
            Parent rootUser = (Parent) fxmlLoaderUser.load();
            UserController usc = fxmlLoaderUser.<UserController>getController();
            Scene sceneUser = new Scene(rootUser);

            svc.addStringConsole("Server has started ..", ServerConstants.SYSTEM_MESSAGE);

            ssc = new ServerSocketConnection(svc, usc);
            svc.setServerSocketConnection(ssc);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("interfaces/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Chat Server");
            primaryStage.show();

            // switch dintre server si partea de managing users ca interfata
            svc.btnManageUser.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    primaryStage.setScene(sceneUser);
                    primaryStage.setTitle("Manage Users");
                    primaryStage.show();
                }
            });

            usc.btnBackServer.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Chat Server");
                    primaryStage.show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws IOException {
        //if(ssc.isStart())
        ssc.stopServer();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
