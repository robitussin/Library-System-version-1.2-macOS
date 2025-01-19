package ui.addBook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import data.Book;
import database.DatabaseHandler;
import alert.AlertMaker;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

   // @FXML
    //private TextField id;

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private TextField qtytextfield;

    @FXML
    private BorderPane rootPane;

    @FXML
    private ChoiceBox typechoicebox;

    @FXML
    private Label categorylabel;

    @FXML
    private Label qtylabel;

    @FXML
    private ChoiceBox categorychoicebox;

    @Override
    public void initialize(URL url, ResourceBundle rb){

        typechoicebox.getItems().addAll("Fiction", "Non Fiction");
        categorylabel.setVisible(false);
        categorychoicebox.setVisible(false);
        qtytextfield.setVisible(false);
        qtylabel.setVisible(false);
    }

    public void addBookButtonHandler(ActionEvent actionEvent) {
        //String bookID = id.getText();
        String bookName = title.getText();
        String bookAuthor = author.getText();
        String bookPublisher = publisher.getText();
        String type = typechoicebox.getValue().toString();
        String category = categorychoicebox.getValue().toString();

        String qty = qtytextfield.getText();
        qty = qty.trim();
        Integer quantity = Integer.parseInt(qty);

        // Remove leading and trailing white space
        //bookID = bookID.trim();
        bookName = bookName.trim();
        bookAuthor = bookAuthor.trim();
        bookPublisher = bookPublisher.trim();

        Book book = new Book("", bookName, bookAuthor, bookPublisher, type, category, "", "", "");

        Boolean success = false;

        for (int i = 1; i <= quantity; i++) {

            if (DatabaseHandler.addBook(book)) {
                success = true;
            }
        }
        if(success) {
            AlertMaker.showSuccessfulAlert("Success", "A new book has been added!");
        }
        else{
            AlertMaker.showErrorAlert("Failed", "Failed to add new book!");
        }

        title.clear();
        author.clear();
        publisher.clear();
        typechoicebox.getItems().clear();
        categorychoicebox.getItems().clear();
        qtytextfield.clear();

    }

   public void showCategory(ActionEvent Event) {
      // Show Category label and choicebox
       categorylabel.setVisible(true);
       categorychoicebox.setVisible(true);
       qtytextfield.setVisible(true);
       qtylabel.setVisible(true);

       // Clear selections upon changing book type
       categorychoicebox.getSelectionModel().clearSelection();
       categorychoicebox.getItems().clear();

       if (typechoicebox.getValue() == "Fiction") {

           categorychoicebox.getItems().addAll("Action and Adventure", "Anthology", "Classic", "Action and Adventure",
                   "Anthology",
                   "Classic",
                   "Comic and Graphic Novel",
                   "Crime and Detective",
                   "Drama",
                   "Fable",
                   "Fairy Tale",
                   "Fan-Fiction",
                   "Fantasy",
                   "Historical Fiction",
                   "Horror",
                   "Humor",
                   "Legend",
                   "Magical Realism",
                   "Mystery",
                   "Mythology",
                   " Realistic Fiction",
                   "Romance",
                   "Satire",
                   "Science Fiction",
                   "Short Story",
                   "SuspenseThriller");
       }
       else
       {
           categorychoicebox.getItems().addAll("Biography/Autobiography",
                   "Essay","Memoir",
                   "Narrative Nonfiction",
                   "Periodicals",
                   "Reference Books",
                   "Self-help Book",
                   "Speech",
                   "Textbook");
       }
   }

    @FXML
    private void cancelButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
