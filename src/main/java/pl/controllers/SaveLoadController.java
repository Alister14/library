package pl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.Data.BorrowsToXml;

import java.io.File;

public class SaveLoadController {

    @FXML
    private TextField savePathTextField;

    @FXML
    private TextField loadPathTextField;

    public void loadDataBase(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage window = new Stage();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));
        File file = fileChooser.showOpenDialog(window);
        if (file != null) {
            System.out.println("File: " + file.getAbsolutePath());
        }
        String path = file.getAbsolutePath();
        loadPathTextField.setText(path);
    }


    public void saveDataBase() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Okno zapisu");
        Stage window = new Stage();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml"));

        File file = fileChooser.showSaveDialog(window);

        BorrowsToXml borrowsToXml = new BorrowsToXml();
        try {
            borrowsToXml.saveTextToFile(file.getAbsolutePath());
        } catch (NullPointerException exception) {
        }
        String path = file.getAbsolutePath();
        savePathTextField.setText(path);
    }
}
