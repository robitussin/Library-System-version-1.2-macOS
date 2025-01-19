package ui.admin.editAccount;

import alert.AlertMaker;
import data.Account;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EditAccountController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField usernametextfield;

    @FXML
    private TextField newpasswordtextfield;

    @FXML
    private boolean updateButtonHandler(ActionEvent event){

        String username = usernametextfield.getText();

        String newpassword = newpasswordtextfield.getText();

        username = username.trim();
        newpassword = newpassword.trim();

        if(username.length() == 0)
        {
            AlertMaker.showErrorAlert("Failed","username is empty");
            return false;
        }

        if(newpassword.length() == 0)
        {
            AlertMaker.showErrorAlert("Failed","password is empty!");
            return false;
        }

        Account account = new Account(username, newpassword, "", "");

        // Check if account exits, reuse function from login
        if(!DatabaseHandler.checkAccountExists(account)){
            AlertMaker.showSimpleAlert("Error","Username does not exist!");
            return false;
        }

        if(DatabaseHandler.comparePassword(account))
        {
            AlertMaker.showErrorAlert("Failed","Please do not use old password");
            return false;
        }

        if(DatabaseHandler.updateAccountPassword(account))
        {
            AlertMaker.showSuccessfulAlert("Success","Password updated");
            closeStage();
            return true;
        }
        else
        {
            AlertMaker.showErrorAlert("Failed","Password update failed");
            return false;
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void closeStage() {
        ((Stage) rootPane.getScene().getWindow()).close();
    }
}
