package pl.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.Data.DatabaseHandling;
import pl.struckture.Reader;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;

public class ListReadersController {


    ArrayList<Reader> readers = DatabaseHandling.getReaders();
    @FXML
    private TextField filterByFirstnameField;

    @FXML
    private TextField filterBySurnameField;


    @FXML
    private TextField FilterByPhoneNumberField;

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

    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("FirstName"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("SurName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Reader, Integer>("PhoneNumber"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Reader, java.sql.Date>("BirthDate"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Reader, String>("Email"));
        clientsTable.getItems().setAll(readers);

    }

    public void filter() {
        ArrayList<Reader> temp = new ArrayList<>();
        for (Reader reader : readers) {
            if (reader.getFirstName().toLowerCase(Locale.ROOT).contains(filterByFirstnameField.getText().toLowerCase(Locale.ROOT)) &&
                    reader.getSurName().toLowerCase(Locale.ROOT).contains(filterBySurnameField.getText().toLowerCase(Locale.ROOT)) &&
                    reader.getPhoneNumber().toString().startsWith(FilterByPhoneNumberField.getText()))
                temp.add(reader);
        }

        clientsTable.getItems().setAll(temp);
    }
}

