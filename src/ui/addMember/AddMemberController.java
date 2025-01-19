package ui.addMember;

import alert.AlertMaker;
import database.DatabaseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import data.Member;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AddMemberController {

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField emailaddress;

    @FXML
    private BorderPane rootPane;

    public void addMemberButtonHandler(ActionEvent event){

        //String memberID = id.getText();
        String memberName = name.getText();
        String memberEmailAddress = emailaddress.getText();

        // Remove leading and trailing white space
        //memberID = memberID.trim();
        memberName = memberName.trim();
        memberEmailAddress = memberEmailAddress.trim();

        Member member = new Member("", memberName, memberEmailAddress);

        if(DatabaseHandler.addMember(member))
        {
            AlertMaker.showSuccessfulAlert("Success", "A new member has been added!");
        }
        else
        {
            AlertMaker.showErrorAlert("Failed","Failed to add new member!");
        }

        id.clear();
        name.clear();
        emailaddress.clear();
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
