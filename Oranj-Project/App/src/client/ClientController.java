package client;

import client.useful.ServerConstants;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.*;
import javafx.util.Callback;

import java.net.*;
import java.util.*;
import java.io.*;

/**
 * @author Ionita Andra 2A7
 * Controller la client
 */
public class ClientController implements Initializable {
    @FXML
    public Button btnSend;

    @FXML
    public Button btnExit;

    @FXML
    public Button btnSendFile;

    @FXML
    public Label txtName;

    @FXML
    public TextFlow txtConsoleClient;

    @FXML
    public ComboBox<String> comboBoxMember;

    @FXML
    public ScrollPane txtScrollPaneClient;

    @FXML
    public TextField txtSend;

    @FXML
    public TableView<User> tblMemberList;

    @FXML
    public TableColumn<User, String> tblColName;

    @FXML
    public TableColumn<User, String> tblColIP;

    @FXML
    public TableColumn<User, Integer> tblColPort;

    @FXML
    public TableColumn<User, Boolean> tblColBlock;

    public ObservableList<User> listObvUser = FXCollections.observableArrayList();
    public ObservableList<String> listObvMemberName = FXCollections.observableArrayList();
    private ClientSocketConnection csc;


    //functie apelata de fxml loader cand initializarea e completa
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert btnSend != null;

        txtSend.requestFocus();
        txtSend.setOnAction(event -> {
            try {
                performSend(txtSend.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // setam lista cu useri care sunt intrati in aplicatie
        tblColName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        tblColIP.setCellValueFactory(new PropertyValueFactory<User, String>("IPAddress"));
        tblColPort.setCellValueFactory(new PropertyValueFactory<User, Integer>("port"));

        // setam blocul cu checkbox-uri care oate bloca o persoana sau mai multe, ariabila is blocked se actualizeaza atunci cand caseta este sau nu bifata
        tblColBlock.setCellValueFactory(new PropertyValueFactory<User, Boolean>("isBlocked"));
        tblColBlock.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<User, Boolean> param) {
                return param.getValue().isBlockedProperty();
            }
        });
        tblColBlock.setCellFactory(CheckBoxTableCell.forTableColumn(tblColBlock));
        tblColBlock.setEditable(true);
        tblMemberList.setItems(listObvUser);

        // setam combo box-ul pentru "send to" ca sa putem trimite mesaje private
        listObvMemberName.add("All members");
        comboBoxMember.setItems(listObvMemberName);
        comboBoxMember.setValue("All members");

    }


    //implementare buton send
    @FXML
    private void handleButtonActionSend(ActionEvent event) throws Exception {
        performSend(txtSend.getText());
    }

    public void performSend(String message) throws IOException {
        if (!message.trim().equals("")) {
            if (comboBoxMember.getValue() == null || comboBoxMember.getValue().equals("All members")) {
                // Send broadcast message
                this.csc.sendMessage(message);
            } else {
                // Send private message
                String sender = txtName.getText();
                String receiver = comboBoxMember.getValue();
                this.csc.sendMessage(ServerConstants.PRIVATE_MESSAGE, comboBoxMember.getValue() + "|" + message);
                if (!sender.equals(receiver)) {
                    addStringConsole("From " + sender + " to " + receiver + "> " + message, ServerConstants.PRIVATE_MESSAGE);
                }
            }
        }
        txtSend.clear();
        txtSend.requestFocus();
    }

    //implementam buton de exit
    @FXML
    private void handleButtonActionExit(ActionEvent event) throws Exception {
        csc.stopClient();

        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    //functie care adauga string trimis la panel-ul de text si care coloreaza in functie de tipul de mesaj
    public void addStringConsole(String str, int id) {

        Text name;
        Text message;

        if (id == ServerConstants.BROADCAST_MESSAGE) {
            name = new Text(str.substring(0, str.indexOf(">") + 1));
            message = new Text(str.substring(str.indexOf(">") + 1, str.length()) + "\n");

            name.setFill(Color.DARKORANGE);

        } else if (id == ServerConstants.SYSTEM_MESSAGE) {
            name = new Text("");
            message = new Text(str + "\n");

        } else if (id == ServerConstants.PRIVATE_MESSAGE) {
            name = new Text(str.substring(0, str.indexOf(">") + 1));
            message = new Text(str.substring(str.indexOf(">") + 1, str.length()) + "\n");

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
                txtConsoleClient.getChildren().addAll(name, message);
                txtScrollPaneClient.setVvalue(1.0);
            }
        });

        System.out.println(str);

    }

    public void setClientSocketConnection(ClientSocketConnection csc) {
        this.csc = csc;
    }

}
