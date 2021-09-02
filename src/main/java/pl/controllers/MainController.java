package pl.controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.utils.DialogsUtils;
import pl.utils.FxmlUtils;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.application.Application.STYLESHEET_CASPIAN;
import static javafx.application.Application.STYLESHEET_MODENA;

public class MainController implements Initializable {


    @FXML
    private ImageView logo;

    @FXML
    private BorderPane borderPaine;

    @FXML
    private pl.controllers.TopMenuButtonsController topMenuButtonsController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        topMenuButtonsController.setMainController(this);//Ta funkcja pozwala stereować topMenuButtonem
        logo.setImage(LogoController.getLogoImage());
    }

    @FXML
    public void setCenter(String fxmlPath) {

        borderPaine.setCenter(FxmlUtils.fxmlLoader(fxmlPath));
    }


    public void closseAplication() {//Funkcja wyświetla okienko dialogowe które pyta nas czy chcemy wyjść
        //Jeśli tak to wysła kod zakończenia aplikacji 0
        Optional<ButtonType> result = DialogsUtils.confirmationDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }

    }

    public void setCaspian() {
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);//Funkcja ustawia stylk na Caspian, który jest
        // standardowym stylem
    }

    public void setModena() {
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);//Funkcja ustawia stylk na Caspian, który jest
        // standardowym stylem
    }

    public void setAlwayOnTop(ActionEvent event) {
        Stage stage = (Stage) borderPaine.getScene().getWindow();
        boolean value = ((CheckMenuItem) event.getSource()).selectedProperty().get();//Funkcja zbiera informację czy
        // opcja "Zawsze na wierzchu" jest zaznaczona czy nie
        stage.setAlwaysOnTop(value);//Funkcja ustawia okno aplikacji by było zawsze na wierzchu


    }

    public void aboutUs() {
        DialogsUtils.dialogApplicatio();
    }


}
