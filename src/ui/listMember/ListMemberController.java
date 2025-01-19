package ui.listMember;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListMemberController implements Initializable {

    ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private BorderPane rootPane;

    @FXML
    private TableView<Member> tableView;

    @FXML
    private TableColumn<Member, String> idCol;

    @FXML
    private TableColumn<Member, String> nameCol;

    @FXML
    private TableColumn<Member, String> emailCol;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeCol();
        loadData();
    }

    private void initializeCol(){

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData(){

        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();

        String query = "SELECT * FROM memberlist";
        ResultSet result = handler.execQuery(query);

        try {
            while (result.next())
            {
                String id = result.getString("ID");
                String name = result.getString("Name");
                String email = result.getString("Email");
                list.add(new Member(id, name, email));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        tableView.setItems(list);
    }

    @FXML
    private void exitButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public static class Member {

        private final SimpleStringProperty id;
        private final SimpleStringProperty name;
        private final SimpleStringProperty email;

        public Member(String id, String name, String email) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);

        }

        public String getId() {
            return id.get();
        }

        public String getName() {
            return name.get();
        }

        public String getEmail() {
            return email.get();
        }
    }

    @FXML
    public void refreshButtonHandler(){
        initializeCol();
        loadData();
    }
}
