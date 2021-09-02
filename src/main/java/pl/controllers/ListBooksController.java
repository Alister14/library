package pl.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.Data.DatabaseHandling;
import pl.struckture.Book;

import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ListBooksController implements Initializable {
    ArrayList<Book> books = DatabaseHandling.getBooks();

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
    private TextField filterByAuthorField;
    @FXML
    private TextField filterByPublisherField;

    public void filter() {
        String filterTitle = filterByTitleField.getText();
        String filterAuthor = filterByAuthorField.getText();
        String filterPublisher = filterByPublisherField.getText();
        ArrayList<Book> temp = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase(Locale.ROOT).contains(filterTitle.toLowerCase(Locale.ROOT)) &&
                    book.getAuthor().toLowerCase(Locale.ROOT).contains(filterAuthor.toLowerCase(Locale.ROOT)) &&
                    book.getPublisher().toLowerCase(Locale.ROOT).contains(filterPublisher.toLowerCase(Locale.ROOT)))
                temp.add(book);
        }

        booksTable.getItems().setAll(temp);
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
    }
}
