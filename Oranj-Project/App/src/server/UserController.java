package server;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import server.db.Database;
import server.security.Encrypt;

/**
 * @author Ionita Andra 2A7
 * Controllerul pentru useri
 */
public class UserController implements Initializable {

    @FXML
    Button btnAddUser;

    @FXML
    Button btnRemoveUser;

    @FXML
    Label txtMessage;

    @FXML
    TextField txtUserName;

    @FXML
    TextField txtPassword;

    @FXML
    Button btnBackServer;

    @FXML
    TableView<User> tblUser;

    @FXML
    TableColumn<User, String> tblColUserName;

    @FXML
    TableColumn<User, String> tblColPassword;

    public ObservableList<User> listUser = FXCollections.observableArrayList();
    public List<User> listU = new ArrayList<User>();
    public Database db = Database.getInstance();

    @Override // metoda apelata de fxml loader cand initializarea este completa
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        //luam lista cu useri
        try {

            listU.clear();
            ResultSet rs = db.setResultSet("SELECT USERNAME, PASSWORD FROM ORANJ_USERS");
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2));
                listU.add(user);
                listUser = FXCollections.observableArrayList(listU);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        txtMessage.setText("");
        tblColUserName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        tblColPassword.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        tblUser.setItems(listUser);

    }

    //adaugam useri in baza de date
    @FXML
    private void handleButtonActionAdd(ActionEvent event) throws Exception {
        if (!txtUserName.getText().trim().equals("") && !txtPassword.getText().trim().equals("")) {
            if (!isDuplicateName(txtUserName.getText().trim())) {
                //adaugam user-ul la baza de date si la tabelul din server
                User user = new User(txtUserName.getText().trim(), Encrypt.encrypt(txtPassword.getText().trim()));
                listUser.add(user);

                try {

                    ResultSet rs = db.setResultSet("INSERT INTO ORANJ_USERS(USERNAME, PASSWORD) VALUES('" + txtUserName.getText().trim()
                            + "','" + Encrypt.encrypt(txtPassword.getText().trim()) + "')");

                    txtMessage.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                txtMessage.setText("This username is already registered");
            }
        }

        txtUserName.clear();
        txtPassword.clear();
    }


    //stergem useri din baza de date
    @FXML
    private void handleButtonActionRemove(ActionEvent event) throws Exception {
        //stergem userul din lista
        listUser.remove(tblUser.getSelectionModel().getSelectedIndex());
        try {
            String username = listUser.get(tblUser.getSelectionModel().getSelectedIndex() % 10).getUserName();
            ResultSet rs = db.setResultSet("DELETE FROM ORANJ_USERS WHERE USERNAME = '" + username + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //verificam logarea
    public boolean isLoginSuccessful(String username, String password) {
        ArrayList<User> users = new ArrayList<User>(listUser);
        boolean flag = false;
        for (User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                flag = true;
                //break;
            }
        }
        return flag;
    }


    //verificam daca mai exista acelasi username
    public boolean isDuplicateName(String username) {
        ArrayList<User> users = new ArrayList<User>(listUser);
        boolean flag = false;
        for (User user : users) {
            if (user.getUserName().equals(username)) {
                flag = true;
                //break;
            }
        }
        return flag;
    }

    @FXML
    private void handleButtonActionBackServer(ActionEvent event) throws Exception {

    }

}