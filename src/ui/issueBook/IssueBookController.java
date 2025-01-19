package ui.issueBook;

import alert.AlertMaker;
import database.DatabaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import data.Book;
import data.Member;
import javafx.stage.Stage;

public class IssueBookController {

    @FXML
    private TextField bookidtxtfield;

    @FXML
    private TextField memberidtxtfield;

    @FXML
    private BorderPane rootPane;

    @FXML
    private DatePicker datePicker;

    public boolean issueBookButtonHandler(ActionEvent event){

        String bookID = bookidtxtfield.getText();
        String memberID = memberidtxtfield.getText();

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateIssuedToMember = dateFormat.format(date);
        String isAvailable = "0";

        LocalDate localdate = datePicker.getValue();

        String dateOfReturn = localdate.toString();

        Book book = new Book(bookID, "", "", "", "", "", isAvailable, dateIssuedToMember, dateOfReturn);

        Member member = new Member(memberID, "", "");

        if(!DatabaseHandler.checkBookExists(book)){
            AlertMaker.showSimpleAlert("Error","Cannot issue book. Book does not exist");
            return false;
        }

        if(!DatabaseHandler.checkMemberExists(member)){
            AlertMaker.showSimpleAlert("Error","Cannot issue book. Member does not exist");
            return false;
        }

        if(DatabaseHandler.checkIssuedBook(book)){
            AlertMaker.showSimpleAlert("Error","Cannot issue book. Book already issued");
            return false;
        }

        if(DatabaseHandler.issueBook(book, member))
        {
            AlertMaker.showSuccessfulAlert("Success", "Book has been issued!");
            bookidtxtfield.clear();
            memberidtxtfield.clear();
            datePicker.getEditor().clear();
            return true;
        }
        else
        {
            AlertMaker.showErrorAlert("Failed", "Failed to issue book");
            return false;
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
