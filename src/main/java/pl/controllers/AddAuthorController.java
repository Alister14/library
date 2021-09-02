package pl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.Data.DatabaseHandling;

import java.sql.Date;

public class AddAuthorController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField surNameField;

    @FXML
    private DatePicker birtdayPicker;

    @FXML
    private Button addUserButton;
    @FXML
    private Text errorText;


    public void checkIfButtonCanBePressed() {
        if (firstNameField.getText().isEmpty() || surNameField.getText().isEmpty() || birtdayPicker.getValue() == null) {
            if (firstNameField.getText().isEmpty()) {
                errorText.setText("Pole imie jest puste!");
            }
            if (surNameField.getText().isEmpty()) {
                errorText.setText("Pole nazwisko jest puste!");
            }

            if (birtdayPicker.getValue() == null) {
                errorText.setText("Pole data urodzenia jest puste!");
            }
            addUserButton.setDisable(true);
        } else {
            errorText.setText("Dobra robota:)");
            addUserButton.setDisable(false);
        }
    }

    public void initialize() {

    }

    public void addAuthor(ActionEvent event) {

        DatabaseHandling.addNewAuthor(firstNameField.getText(),
                surNameField.getText(),
                Date.valueOf(birtdayPicker.getValue()));
    }
}
