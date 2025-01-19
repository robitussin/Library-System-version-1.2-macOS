package ui.manageMember;

import alert.AlertMaker;
import data.Member;
import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManageMemberController {

    ObservableList<Member> list = FXCollections.observableArrayList();

    @FXML
    private BorderPane rootpane;

    @FXML
    private TextField memberdidtextfield;

    @FXML
    private TableView<Member> tableview;

    @FXML
    private TableColumn memberidcol;

    @FXML
    private TableColumn namecol;

    @FXML
    private TableColumn emailcol;

    @FXML
    private void searchButtonHandler(ActionEvent event){

        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();

        String memberID = memberdidtextfield.getText();

        String query = "SELECT * FROM memberlist WHERE ID LIKE '%"+ memberID + "%'";
        ResultSet result = handler.execQuery(query);

        try {
            while (result.next())
            {
                String id = result.getString("ID");
                String name = result.getString("Name");
                String email = result.getString("Email");

                list.add(new Member(id,name, email));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        memberidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableview.setItems(list);
    }

    @FXML
    private void deleteButtonHandler(){

        if(AlertMaker.showConfirmationAlert("Delete Member", "Are you sure you want to delete member?"))
        {
            Member member = tableview.getSelectionModel().getSelectedItem();

            String bookID = (member.getId());

            member = new Member(bookID, "", "");

            if(DatabaseHandler.deleteMember(member))
            {
                AlertMaker.showSuccessfulAlert("Success", "Member has been deleted!");
            }
            else
            {
                AlertMaker.showErrorAlert("Failed","Failed to delete member!");
            }
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootpane.getScene().getWindow();
        stage.close();
    }
}
