package client;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.fxml.*;

import java.io.*;

/**
 * @author Ionita Andra 2A7
 * Main la client
 */
public class Client extends Application {
    ClientSocketConnection csc;

    //dam load la fxml-luri pentru interfata la client login si client chat
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoaderClientLogin = new FXMLLoader(getClass().getResource("interfaces/ClientLogin.fxml"));
            Pane rootClientLogin = (Pane) fxmlLoaderClientLogin.load();
            ClientLoginController cllc = fxmlLoaderClientLogin.<ClientLoginController>getController();

            Scene sceneLogin = new Scene(rootClientLogin);
            sceneLogin.getStylesheets().add(getClass().getResource("interfaces/application.css").toExternalForm());
            primaryStage.setScene(sceneLogin);
            primaryStage.setTitle("Client");
            primaryStage.show();

            FXMLLoader fxmlLoaderClient = new FXMLLoader(getClass().getResource("interfaces/Client.fxml"));
            Parent rootClient = (Parent) fxmlLoaderClient.load();
            ClientController clc = fxmlLoaderClient.<ClientController>getController();

            csc = new ClientSocketConnection(clc, cllc);
            clc.setClientSocketConnection(csc);
            cllc.setClientSocketConnection(csc);

            Scene scene = new Scene(rootClient);
            scene.getStylesheets().add(getClass().getResource("interfaces/application.css").toExternalForm());

            // prima oara afisam ecranul de login
            cllc.setSceneChat(scene);

            // userul poate apasa si enter pentru login
            cllc.btnLogin.setOnAction(event -> {
                cllc.doLogin(scene);
            });

            cllc.txtUserName.setOnAction(event -> {
                cllc.doLogin(scene);
            });

            cllc.txtPassword.setOnAction(event -> {
                cllc.doLogin(scene);
            });

            cllc.txtUserName.requestFocus();
            clc.txtSend.requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws IOException {
        csc.stopClient();
    }

    // lansam aplicatia
    public static void main(String[] args) {
        launch(args);
    }
}

