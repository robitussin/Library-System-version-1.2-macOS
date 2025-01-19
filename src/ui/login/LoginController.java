package ui.login;

import alert.AlertMaker;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.Utility;

public class LoginController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField usernamefield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private void loginButtonHandler(ActionEvent event){

        String uname = usernamefield.getText();

        String pword = passwordfield.getText();

        uname = uname.trim();

        if(DatabaseHandler.validateLogin(uname, pword))
        {
            if(DatabaseHandler.checkAccountStatus(uname)){
                closeStage();
                Utility.loadWindow(getClass().getResource("/ui/main/main.fxml"), "Library System", null);
            }
            else
            {
                AlertMaker.showErrorAlert("Failed", "Account Deactivated!");
            }

        }
        else
        {
            AlertMaker.showErrorAlert("Failed", "Invalid login details!");
        }



    }

    private void closeStage() {

        ((Stage) rootPane.getScene().getWindow()).close();
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    /*
    void loadMain() throws Exception{

        Parent parent = FXMLLoader.load(getClass().getResource("ui/main/main.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Library System");
        stage.setScene(new Scene(parent));
        stage.show();
        Utility.setStageIcon(stage);
    }
    */
}
