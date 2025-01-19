package ui.receiveBook;

import alert.AlertMaker;
import data.Book;
import data.Member;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiveBookController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField bookidtextfield;

    @FXML
    private TextField memberidtextfield;

    public boolean receiveBookButtonHandler(ActionEvent event){

        String bookID = bookidtextfield.getText();

        String memberID = memberidtextfield.getText();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateOfReturn = dateFormat.format(date);
        String isAvailable = "1";

        Book book = new Book(bookID, "", "", "", "", "", isAvailable, "", dateOfReturn);

        Member member = new Member(memberID, "", "");

        if(!DatabaseHandler.checkBookExists(book)){
            AlertMaker.showSimpleAlert("Error","Cannot issue book. Book does not exist");
            return false;
        }

        if(!DatabaseHandler.checkMemberExists(member)){
            AlertMaker.showSimpleAlert("Error","Cannot issue book. Member does not exist");
            return false;
        }

        if(DatabaseHandler.receiveBook(book, member))
        {
            AlertMaker.showSuccessfulAlert("Success", "Book has been received!");
            return true;
        }
        else
        {
            AlertMaker.showErrorAlert("Failed", "Failed to receive book!");
            return false;
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
