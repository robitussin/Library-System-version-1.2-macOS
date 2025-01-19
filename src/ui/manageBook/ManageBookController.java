package ui.manageBook;

import alert.AlertMaker;
import data.Book;
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

public class ManageBookController {

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    private BorderPane rootpane;

    @FXML
    private TextField bookdidtextfield;

    @FXML
    private TableView<Book> tableview;

    @FXML
    private TableColumn bookidcol;

    @FXML
    private TableColumn titlecol;

    @FXML
    private TableColumn authorcol;

    @FXML
    private TableColumn publishercol;

    @FXML
    private TableColumn availcol;

    @FXML
    private void searchButtonHandler(ActionEvent event){

        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();

        String bookID = bookdidtextfield.getText();

        String query = "SELECT * FROM booklist WHERE ID LIKE '%"+ bookID + "%'";
        ResultSet result = handler.execQuery(query);

        try {
            while (result.next())
            {
                String id = result.getString("ID");
                String title = result.getString("Title");
                String author = result.getString("Author");
                String publisher = result.getString("Publisher");
                String type = result.getString("Type");
                String category = result.getString("Category");
                Boolean temp = result.getBoolean("IsAvailable");

                String isAvailable = "";
                if(temp) {
                    isAvailable = "Available";
                }
                else {
                    isAvailable= "Issued";
                }

                list.add(new Book(id, title, author, publisher, type, category, isAvailable, "", ""));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        bookidcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorcol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishercol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availcol.setCellValueFactory(new PropertyValueFactory<>("availability"));

        tableview.setItems(list);
    }

    @FXML
    private void deleteButtonHandler(){

        if(AlertMaker.showConfirmationAlert("Delete Book", "Are you sure you want to delete book?"))
        {
            Book book = tableview.getSelectionModel().getSelectedItem();

            String bookID = (book.getId());

            book = new Book(bookID, "", "", "", "", "", "", "","");

            if(DatabaseHandler.deleteBook(book))
            {
                AlertMaker.showSuccessfulAlert("Success", "Book has been deleted!");
            }
            else
            {
                AlertMaker.showErrorAlert("Failed","Failed to delete  book!");
            }
        }
    }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootpane.getScene().getWindow();
        stage.close();
    }
}
