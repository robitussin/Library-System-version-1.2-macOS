package ui.admin.addAccount;

import alert.AlertMaker;
import data.Account;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AddAccountController{
    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField usernametextfield;

    @FXML
    private TextField passwordtextfield;

    @FXML
    private TextField confirmPasstextfield;

    @FXML
    private boolean addButtonHandler(ActionEvent event){

        String username = usernametextfield.getText();

        String password = passwordtextfield.getText();

        String confirmPassword = confirmPasstextfield.getText();

        String status = "1";

        username = username.trim();
        password = password.trim();
        confirmPassword = confirmPassword.trim();

        if(username.length() == 0)
        {
            AlertMaker.showErrorAlert("Failed","username is empty");
            return false;
        }

        if(password.length() == 0)
        {
            AlertMaker.showErrorAlert("Failed","password is empty\"!");
            return false;
        }

        if(confirmPassword.length() == 0)
        {
            AlertMaker.showErrorAlert("Failed","confirmPassword is empty!");
            return false;
        }

        if(!password.equals(confirmPassword))
        {
            AlertMaker.showErrorAlert("Failed","confirmPassword does not match!");
            return false;
        }

        Account account = new Account(username, password, "", status);

        if(DatabaseHandler.checkAccountExists(account)){
            AlertMaker.showSimpleAlert("Error","Username already exists!");
            return false;
        }

        if(DatabaseHandler.addAccount(account))
        {
            AlertMaker.showSuccessfulAlert("Success", "A new account has been added!");
            usernametextfield.clear();
            passwordtextfield.clear();
            confirmPasstextfield.clear();
            return true;
        }
        else
        {
            AlertMaker.showErrorAlert("Failed","Failed to add new account!");
            return false;
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }


}
