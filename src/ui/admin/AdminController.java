package ui.admin;

import alert.AlertMaker;
import data.Account;
import data.Member;
import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utility.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {

    ObservableList<Account> list = FXCollections.observableArrayList();

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField accountnametextfield;

    @FXML
    private TableView<Account> tableview;

    @FXML
    private TableColumn usernamecol;

    @FXML
    private TableColumn accountcreatedcol;

    @FXML
    private TableColumn isactivecol;

    @FXML
    private void searchButtonHandler(ActionEvent event){

        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();

        String accountName = accountnametextfield.getText();

        String query = "";

        if(accountName.length() > 0){
            query = "SELECT * FROM admin WHERE Username LIKE '%"+ accountName + "%'";
        }
        else
        {
            query = "SELECT * FROM admin";
        }

        ResultSet result = handler.execQuery(query);

        try {
            while (result.next())
            {
                String username = result.getString("Username");
                String accountCreated = result.getString("AccountCreated");
                Boolean status = result.getBoolean("AccountStatus");
                String isActive = "";
                if(status){

                    isActive = "Yes";
                }
                else
                {
                    isActive = "No";
                }

                list.add(new Account(username,"", accountCreated, isActive));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        usernamecol.setCellValueFactory(new PropertyValueFactory<>("username"));
        accountcreatedcol.setCellValueFactory(new PropertyValueFactory<>("accountCreated"));
        isactivecol.setCellValueFactory(new PropertyValueFactory<>("isActive"));

        tableview.setItems(list);
    }

    @FXML
    private void deleteButtonHandler(){

        if(AlertMaker.showConfirmationAlert("Delete Account", "Are you sure you want to delete account?"))
        {
            Account account = tableview.getSelectionModel().getSelectedItem();

            String username = account.getUsername();

            account = new Account(username, "", "", "");

            if(DatabaseHandler.deleteAccount(account))
            {
                AlertMaker.showSuccessfulAlert("Success", "Account has been deleted!");
            }
            else
            {
                AlertMaker.showErrorAlert("Failed","Failed to delete account!");
            }
        }
    }

    public void loadChangePasswordButtonHandler(ActionEvent event){
        Utility.loadWindow(getClass().getResource("/ui/admin/editAccount/edit_account.fxml"), "Add account", null);
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public void loadAddAccountButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/admin/addAccount/add_account.fxml"), "Add account", null);
    }

    @FXML
    private void activateButtonHandler(){

        if(AlertMaker.showConfirmationAlert("Activate Account", "Are you sure you want to activate selected account?"))
        {
            Account account = tableview.getSelectionModel().getSelectedItem();

            String username = account.getUsername();

            String isActive = "1";

            account = new Account(username, "", "",isActive);

            if(DatabaseHandler.updateAccountStatus(account))
            {
                AlertMaker.showSuccessfulAlert("Success", "Account has been activated!");
            }
            else
            {
                AlertMaker.showErrorAlert("Failed","Failed to activate account!");
            }
        }
    }

    @FXML
    private void deactivateButtonHandler(){

        if(AlertMaker.showConfirmationAlert("Activate Account", "Are you sure you want to deactivate selected account?"))
        {
            Account account = tableview.getSelectionModel().getSelectedItem();

            String username = account.getUsername();

            String isActive = "0";

            account = new Account(username, "", "",isActive);

            if(DatabaseHandler.updateAccountStatus(account))
            {
                AlertMaker.showSuccessfulAlert("Success", "Account has been deactivated!");
            }
            else
            {
                AlertMaker.showErrorAlert("Failed","Failed to deactivate account!");
            }
        }
    }

}
