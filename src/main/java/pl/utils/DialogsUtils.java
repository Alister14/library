package pl.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class DialogsUtils {

    // go używać przy wyowołanaich okienek
    public static void dialogApplicatio() {
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);//Tworzy okienko dialogowe
        informationAlert.setTitle("O nas");//Ustawiam tytuł okna i korzystam z gotowych Stringów
        informationAlert.setHeaderText("Twórcy tej aplikacji");//Ustawia głowny tekst
        informationAlert.setContentText("Patryk Zielewski i Krystian Tworek");//Ustawia Tekst opisowy
        informationAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmationDialog() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);//Tworzy okienko które będzie pytało czy
        //napewno chcesz wyjśc z aplikacji
        confirmationDialog.setTitle("EXIT");//Ustawiam tytuł okna i korzystam z gotowych Stringów
        confirmationDialog.setHeaderText("Czy napewno chcesz nas opuścić, będzie nam z tego powodu przykro");
        Optional<ButtonType> result = confirmationDialog.showAndWait();// Ta metoda pokazuję który przycisk został kliknięty
        //
        return result;
    }

    public static Optional<ButtonType> punishDialog(long cost) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);//Tworzy okienko które będzie pytało czy
        //napewno chcesz wyjśc z aplikacji
        confirmationDialog.setTitle("Uwaga książka została przetrzymana");//Ustawiam tytuł okna i korzystam z gotowych Stringów
        confirmationDialog.setHeaderText("Należy się kara w postaci " + cost + "zł");
        Optional<ButtonType> result = confirmationDialog.showAndWait();// Ta metoda pokazuję który przycisk został kliknięty
        //
        return result;
    }

    public static void errorDialog(String error) { //Okienko na ciężkie czasy, kiedy wystąpi błąd
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Tytul");
        errorAlert.setHeaderText("Heder");

        TextArea textArea = new TextArea(error);
        errorAlert.getDialogPane().setContent(textArea);
        errorAlert.showAndWait();
    }

    public static String editDialog(String value) {
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.setTitle("Tytul");
        dialog.setHeaderText("Heder");
        dialog.setContentText("content");
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}

