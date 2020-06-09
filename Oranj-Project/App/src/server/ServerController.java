package server;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import server.useful.ServerConstants;

/**
 * @author Ionita Andra 2A7
 * Controllerul serverului
 */
public class ServerController implements Initializable {

    @FXML
    TextFlow txtConsole;

    @FXML
    ScrollPane txtScrollPane;

    @FXML
    Button btnStart;

    @FXML
    Button btnStop;

    @FXML
    Button btnManageUser;

    @FXML
    ListView<String> lstMember = new ListView<String>();

    @FXML
    TableView<Member> tblMemberList;

    @FXML
    TextField txtPort;

    @FXML
    TableColumn<Member, String> tblColName;

    @FXML
    TableColumn<Member, String> tblColIP;

    @FXML
    TableColumn<Member, Integer> tblColPort;

    public ObservableList<Member> listObvMember = FXCollections.observableArrayList();

    private ServerSocketConnection ssc;

    public ServerController() {
    }

    @Override // apelata de fxml loader cand initializarea e completa
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert txtConsole != null;
        assert btnStop != null;
        assert lstMember != null;
        assert txtPort != null;

        txtPort.setText("5000");
        btnStop.setDisable(true);

        tblColName.setCellValueFactory(new PropertyValueFactory<Member, String>("name"));
        tblColIP.setCellValueFactory(new PropertyValueFactory<Member, String>("IPAddress"));
        tblColPort.setCellValueFactory(new PropertyValueFactory<Member, Integer>("port"));
        tblMemberList.setItems(listObvMember);

    }

    //buton de start a serverului
    @FXML
    private void handleButtonActionStart(ActionEvent event) throws Exception {
        ssc.startServer();
        btnStart.setDisable(true);
        btnStop.setDisable(false);
        txtPort.setDisable(true);
    }

    //buton de stop a serverului
    @FXML
    private void handleButtonActionStop(ActionEvent event) {
        btnStart.setDisable(true);
        btnStop.setDisable(true);
        ssc.stopServer();
        addStringConsole("Server has stopped", ServerConstants.SYSTEM_MESSAGE);
    }

    @FXML
    private void handleButtonActionManageUser(ActionEvent event) {

    }

    //de fiecare data cand se va da un mesaj in chat va aparea si pe server intr-un panel
    //in panel se va scrie cu ajutorul acestei functii
    public void addStringConsole(String str, int id) {
        Text name;
        Text message;

        final var s = str.substring(str.indexOf(">") + 1, str.length()) + "\n";
        if (id == ServerConstants.BROADCAST_MESSAGE) {
            name = new Text(str.substring(0, str.indexOf(">") + 1));
            message = new Text(s);

            name.setFill(Color.DARKORANGE);
        } else if (id == ServerConstants.SYSTEM_MESSAGE) {
            name = new Text("");
            message = new Text(str + "\n");

        } else if (id == ServerConstants.PRIVATE_MESSAGE) {
            name = new Text(str.substring(0, str.indexOf(">") + 1));
            message = new Text(s);

            name.setFill(Color.DARKGREY);

        } else if (id == ServerConstants.ERROR_MESSAGE) {
            name = new Text("");
            message = new Text(str + "\n");

            name.setFill(Color.RED);
            message.setFill(Color.RED);

        } else {
            name = new Text("");
            message = new Text(str + "\n");
        }

        Platform.runLater(new Runnable() {
            public void run() {
                txtConsole.getChildren().addAll(name, message);
                txtScrollPane.setVvalue(1.0);
            }
        });

        System.out.println(str);
    }

    public void setServerSocketConnection(ServerSocketConnection ssc) {
        this.ssc = ssc;
    }
}