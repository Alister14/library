package pl.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.Data.DatabaseHandling;
import pl.struckture.Author;

import java.sql.Date;
import java.util.ArrayList;

public class AddBookController {
    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField publishingYearField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField authorNameField;
    @FXML
    private TextField authorSurnameField;

    @FXML
    private DatePicker birtdayPicker;

    @FXML
    private Text errorText;

    @FXML
    private ComboBox chooseAuthorBox;


    @FXML
    private Button addBookButton;

    public void checkIfButtonCanBePressed() {
        if (bookTitleField.getText().isEmpty() || publisherField.getText().isEmpty() || publishingYearField.getText().isEmpty() ||
                birtdayPicker.getValue() == null || quantityField.getText().isEmpty() || authorNameField.getText().isEmpty() ||
                authorSurnameField.getText().isEmpty()) {

            if (bookTitleField.getText().isEmpty()) {
                errorText.setText("Pole tytuł jest puste!");
            }
            if (publisherField.getText().isEmpty()) {
                errorText.setText("Pole wydawca jest puste!");
            }
            if (publishingYearField.getText().isEmpty()) {
                errorText.setText("Pole rok wydania jest puste!");
            }
            if (birtdayPicker.getValue() == null) {
                errorText.setText("Pole data urodzenia autora jest puste!");
            }
            if (quantityField.getText().isEmpty()) {
                errorText.setText("Pole ilość sztuk jest puste!");
            }
            if (authorNameField.getText().isEmpty()) {
                errorText.setText("Pole imie autora jest puste!");
            }
            if (authorSurnameField.getText().isEmpty()) {
                errorText.setText("Pole nazwisko autora jest puste!");
            }
            addBookButton.setDisable(true);
        } else {
            errorText.setText("Dobra robota:)");
            addBookButton.setDisable(false);
        }
    }

    public void checkIfPublishingYearIsInteger() {
        if (!publishingYearField.getText().matches("\\d+") || publishingYearField.getText().length() > 4) {
            publishingYearField.setText("");

            errorText.setText("Rok wydania musi być liczbą i mieć maksymalnie 4 znaki!");
        } else
            errorText.setText("");
    }

    public void checkIfQuantityIsInteger() {
        if (!quantityField.getText().matches("\\d+")) {
            quantityField.setText("");

            errorText.setText("Ilość sztuk musi być liczbą!");
        } else
            errorText.setText("");
    }


    public void initialize() {
        ArrayList<String> authorsList = new ArrayList<>();
        for (Author author : DatabaseHandling.getAuthors()) {
            authorsList.add(author.getFirstName() + " " + author.getSurName());
        }
        authorsList.add("");

        ObservableList<String> options = FXCollections.observableArrayList(authorsList);

        chooseAuthorBox.getItems().addAll(options);
        chooseAuthorBox.setValue("");
    }


    public void setAuthorInformation() {

        if (chooseAuthorBox.getValue().toString() == "") {
            checkIfButtonCanBePressed();
            authorNameField.setText("");
            authorSurnameField.setText("");
        } else {
            for (Author author : DatabaseHandling.getAuthors()) {
                if ((author.getFirstName() + " " + author.getSurName()).matches(chooseAuthorBox.getValue().toString())) {
                    //LocalDate data=author.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    addBookButton.setDisable(false);
                    authorNameField.setText(author.getFirstName());
                    authorSurnameField.setText(author.getSurName());
                    // birtdayPicker.setValue(data);

                }
            }
        }
    }


    public void addBook(ActionEvent event) {
        Boolean addNewAuthor = false;
        Integer authorId = 0;

        for (Author author : DatabaseHandling.getAuthors()) {
            if (author.getFirstName().matches(authorNameField.getText()) &&
                    author.getSurName().matches(authorSurnameField.getText())) {
                addNewAuthor = false;
                authorId = author.getAuthorId();
                break;
            }
            addNewAuthor = true;
        }

        if (addNewAuthor) {
            DatabaseHandling.addNewAuthor(authorNameField.getText(), authorSurnameField.getText(), Date.valueOf(birtdayPicker.getValue()));
            authorId = DatabaseHandling.getAuthors().get(DatabaseHandling.getAuthors().size() - 1).getAuthorId();
        }

        DatabaseHandling.addNewBook(bookTitleField.getText(),
                publisherField.getText(),
                Integer.parseInt(publishingYearField.getText()),
                (Integer.parseInt(quantityField.getText()) != 0),
                authorId,
                Integer.parseInt(quantityField.getText()));

    }
}
