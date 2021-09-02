package pl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pl.Data.DatabaseHandling;
import pl.struckture.Book;
import pl.struckture.Reader;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddBorrowController implements Initializable {

    ArrayList<Book> books = DatabaseHandling.getBooks();
    ArrayList<Reader> readers = DatabaseHandling.getReaders();
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;
    @FXML
    private TableColumn<Book, Integer> publishingYearColumn;
    @FXML
    private TableColumn<Book, Boolean> avaolabilityColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;
    @FXML
    private TextField filterByTitleField;
    @FXML
    private TextField borrowTimeField;

    @FXML
    private Button borrowButton;

    @FXML
    private Text errorText;
    @FXML
    private TextField filterBySurnameField;
    @FXML
    private TableView<Reader> clientsTable;
    @FXML
    private TableColumn<Reader, String> firstNameColumn;
    @FXML
    private TableColumn<Reader, String> surnameColumn;
    @FXML
    private TableColumn<Reader, Integer> phoneNumberColumn;
    @FXML
    private TableColumn<Reader, Date> birthDateColumn;
    @FXML
    private TableColumn<Reader, String> emailColumn;

    public void filterBooks() {
        String filterTitle = filterByTitleField.getText();
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase(Locale.ROOT).contains(filterTitle.toLowerCase(Locale.ROOT)))
                temp.add(book);
        }
        booksTable.getItems().setAll(temp);
    }

    public void checkIfBorrowTimeIsInteger() {
        if (!borrowTimeField.getText().matches("\\d+")) {
            borrowTimeField.setText("");

            errorText.setText("Czas wypożyczenia musi być liczbą!");
        } else
            errorText.setText("");

    }

    public void filterReader() {
        ArrayList<Reader> temp = new ArrayList<>();
        for (Reader reader : readers) {
            if (reader.getSurName().toLowerCase(Locale.ROOT).contains(filterBySurnameField.getText().toLowerCase(Locale.ROOT)))
                temp.add(reader);
        }

        clientsTable.getItems().setAll(temp);
    }

    public void displaySelected(MouseEvent mouseEvent) {
        System.out.println(booksTable.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("Publisher"));
        publishingYearColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("PublishingYear"));
        avaolabilityColumn.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("Available"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("Quantity"));
        booksTable.getItems().setAll(books);

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("FirstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("SurName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Reader, Integer>("PhoneNumber"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Reader, java.sql.Date>("BirthDate"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("Email"));
        clientsTable.getItems().setAll(readers);

    }


    public void addNewBorrow(ActionEvent event) {
        if (booksTable.getSelectionModel().getSelectedItem().getAvailable() == true) {
            Integer readerId = clientsTable.getSelectionModel().getSelectedItem().getReaderId();
            Integer booksId = booksTable.getSelectionModel().getSelectedItem().getBooktId();
            DatabaseHandling.addNewBorrow(Date.valueOf(java.time.LocalDate.now()), booksId, readerId, Integer.parseInt(borrowTimeField.getText()));
        } else errorText.setText("Uppsss podana pozycja jest w tej chwili niedostępna");

    }

    public void testButton(ActionEvent event) {
        Integer readerId = clientsTable.getSelectionModel().getSelectedItem().getReaderId();
        Integer booksId = booksTable.getSelectionModel().getSelectedItem().getBooktId();
        if (readerId == null || booksId == null || borrowTimeField.getText().isEmpty()) {
            if (readerId == null)
                errorText.setText("Nie wybrałeś czytelnika");
            if (booksId == null)
                errorText.setText("Nie wybrałeś książki");
            if (borrowTimeField.getText().isEmpty())
                errorText.setText("Nie wybrałeś czasu wypożyczenia");
            borrowButton.setDisable(true);
        } else
            borrowButton.setDisable(false);
    }
}
