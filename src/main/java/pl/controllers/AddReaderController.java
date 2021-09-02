package pl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pl.Data.DatabaseHandling;

import java.sql.Date;

public class AddReaderController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField surNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNumberField;


    @FXML
    private DatePicker birtdayPicker;

    @FXML
    private Button addUserButton;

    @FXML
    private Text errorText;

    public void checkIfButtonCanBePressed() {
        if (firstNameField.getText().isEmpty() || surNameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                phoneNumberField.getText().isEmpty() || birtdayPicker.getValue() == null) {
            if (firstNameField.getText().isEmpty()) {
                errorText.setText("Pole imie jest puste!");
            }
            if (surNameField.getText().isEmpty()) {
                errorText.setText("Pole nazwisko jest puste!");
            }
            if (emailField.getText().isEmpty()) {
                errorText.setText("Pole email jest puste!");
            }
            if (phoneNumberField.getText().isEmpty()) {
                errorText.setText("Pole telefon jest puste!");
            }
            if (birtdayPicker.getValue() == null) {
                errorText.setText("Pole data urodzenia jest puste!");
            }
            addUserButton.setDisable(true);
        } else {
            errorText.setText("Dobra robota:)");
            addUserButton.setDisable(false);
        }
        if (phoneNumberField.getText().length() > 9) {
            errorText.setText("Numer telefonu powinien mieć maksymalnie 9 znaków!");
            addUserButton.setDisable(true);
        }
    }


    public void checkIfPhoneNumberIsInteger() {
        if (!phoneNumberField.getText().matches("\\d+")) {
            phoneNumberField.setText("");

            errorText.setText("Numer telefonu musi być liczbą!");
        } else
            errorText.setText("");
    }

    public void initialize() {

    }

    public void addUser(ActionEvent event) {

        DatabaseHandling.addNewReader(firstNameField.getText(),
                surNameField.getText(),
                Integer.parseInt(phoneNumberField.getText()),
                Date.valueOf(birtdayPicker.getValue()),
                emailField.getText());
    }

}
