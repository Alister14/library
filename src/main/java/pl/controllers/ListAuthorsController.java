package pl.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.Data.DatabaseHandling;
import pl.struckture.Author;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class ListAuthorsController implements Initializable {

    ArrayList<Author> authors = DatabaseHandling.getAuthors();

    @FXML
    private TableView<Author> authorTableViewTable;

    @FXML
    private TableColumn<Author, String> firstNameColumm;
    @FXML
    private TableColumn<Author, String> surNameCulmn;
    @FXML
    private TableColumn<Author, Date> dataOfBirhdayColumn;
    @FXML
    private TableColumn<Author, Integer> idColumn;


    @FXML
    private TextField filterByFirtsNameField;
    @FXML
    private TextField filterBySurNameField;


    public void filter() {
        String filterFirstName = filterByFirtsNameField.getText();
        String filterSurName = filterBySurNameField.getText();
        ArrayList<Author> temp = new ArrayList<>();
        for (Author author : authors) {
            if (author.getFirstName().toLowerCase(Locale.ROOT).contains(filterFirstName.toLowerCase(Locale.ROOT)) &&
                    author.getSurName().toLowerCase(Locale.ROOT).contains(filterSurName.toLowerCase(Locale.ROOT)))
                temp.add(author);
        }
        authorTableViewTable.getItems().setAll(temp);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumm.setCellValueFactory(new PropertyValueFactory<Author, String>("FirstName"));
        surNameCulmn.setCellValueFactory(new PropertyValueFactory<Author, String>("SurName"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Author, Integer>("AuthorId"));
        dataOfBirhdayColumn.setCellValueFactory(new PropertyValueFactory<Author, java.sql.Date>("BirthDate"));
        authorTableViewTable.getItems().setAll(authors);
    }
}
