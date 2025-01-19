package ui.main;

import database.DatabaseHandler;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import utility.Utility;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.stage.Stage;

public class Controller implements Initializable{

    ObservableList<Book> list = FXCollections.observableArrayList();

    @FXML
    private BorderPane rootPane;

    @FXML
    private TableView<Book> tableView;

    @FXML
    private TableColumn<Book, String> bookidcol;

    @FXML
    private TableColumn<Book, String>  memberidcol;

    @FXML
    private TableColumn<Book, String>  dateissuedcol;

    @FXML
    private TableColumn<Book, String>  returndatecol;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        initializeCol();
        loadData();
    }

    private void initializeCol(){

        bookidcol.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        memberidcol.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        dateissuedcol.setCellValueFactory(new PropertyValueFactory<>("dateIssued"));
        returndatecol.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    private void loadData(){

        list.clear();

        DatabaseHandler handler = DatabaseHandler.getInstance();

        String query = "SELECT * FROM issuedbooklist";
        ResultSet result = handler.execQuery(query);

        try {
            while (result.next())
            {
                String id = result.getString("BookID");
                String title = result.getString("MemberID");
                String dateIssued = result.getString("DateIssued");
                String returnDate = result.getString("ReturnDate");

                list.add(new Book(id, title, dateIssued, returnDate));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        tableView.setItems(list);
    }

    public void refreshList(){
        initializeCol();
        loadData();
    }

    public void logoutButtonHandler(ActionEvent event){

        ((Stage) rootPane.getScene().getWindow()).close();
        Utility.loadWindow(getClass().getResource("/ui/login/login.fxml"), "Add Book", null);
    }

    public static class Book {

        private final SimpleStringProperty bookID;
        private final SimpleStringProperty memberID;
        private final SimpleStringProperty dateIssued;
        private final SimpleStringProperty returnDate;

        public Book(String bookID, String memberID, String issuedDate, String returnDate) {

            this.bookID = new SimpleStringProperty(bookID);
            this.memberID = new SimpleStringProperty(memberID);
            this.dateIssued = new SimpleStringProperty(issuedDate);
            this.returnDate = new SimpleStringProperty(returnDate);
        }

        public String getBookID() {
            return bookID.get();
        }

        public String getMemberID() {
            return memberID.get();
        }

        public String getDateIssued() {
            return dateIssued.get();
        }

        public String getReturnDate() {
            return returnDate.get();
        }
    }


    public void loadAddBookButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/addBook/add_book.fxml"), "Add Book", null);
    }

    public void loadAddMemberButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/addMember/add_member.fxml"), "Add Member", null);
    }

    public void loadListBookButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/listBook/list_book.fxml"), "List Book", null);
    }

    public void loadListMemberButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/listMember/list_member.fxml"), "List Member", null);
    }

    public void loadIssueBookButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/issueBook/issue_book.fxml"), "Issue Book", null);
    }

    public void loadIReceiveBookButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/receiveBook/receive_book.fxml"), "Receive Book", null);
    }

    public void loadManageBookButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/manageBook/manage_book.fxml"), "Manage Book", null);
    }

    public void loadManageMemberButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/manageMember/manage_member.fxml"), "Manage Member", null);
    }

    public void loadAdminButtonHandler(ActionEvent actionEvent){

        Utility.loadWindow(getClass().getResource("/ui/admin/admin.fxml"), "Admin", null);
    }
}
