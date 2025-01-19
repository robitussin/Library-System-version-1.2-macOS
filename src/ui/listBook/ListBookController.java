package ui.listBook;

import database.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListBookController implements Initializable {

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    private BorderPane rootPane;

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, String> qtyCol;

    @FXML
    private TableColumn<Book, String>  titleCol;

    @FXML
    private TableColumn<Book, String>  authorCol;

    @FXML
    private TableColumn<Book, String>  publisherCol;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeCol();
        loadData();
    }

    private void initializeCol(){

        qtyCol.setCellValueFactory(new PropertyValueFactory<>("qty"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
    }

    @FXML
    private void exitButtonHandler(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    private void loadData(){

        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();

        String query = "SELECT COUNT(isAvailable) as TotalAvailable, Title, Author, Publisher FROM booklist WHERE isAvailable = '1' GROUP BY Title";
        ResultSet result = handler.execQuery(query);

        try {
            while (result.next())
            {
                String qty = result.getString("TotalAvailable");
                String title = result.getString("Title");
                String author = result.getString("Author");
                String publisher = result.getString("Publisher");

                list.add(new Book(qty, title, author, publisher));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        tableView.setItems(list);
    }


    public static class Book {

        private final SimpleStringProperty qty;
        private final SimpleStringProperty title;
        private final SimpleStringProperty author;
        private final SimpleStringProperty publisher;

        public Book(String qty, String title, String author, String pub) {

            //this.id = new SimpleStringProperty();
            this.qty = new SimpleStringProperty(qty);
            this.title = new SimpleStringProperty(title);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(pub);
        }

        public String getQty() {
            return qty.get();
        }

        public String getTitle() {
            return title.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        /*
        public String getAvail() {
            return avail.get();
        }
         */
    }

    @FXML
    public void refreshButtonHandler(ActionEvent event){
        initializeCol();
        loadData();
    }
}
