package pl.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public class FxmlUtils {
    public static Pane fxmlLoader(String fxmlPath) {//Refactoring mający na celu zmniejszenie kodu
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        try {
            return loader.load();
        } catch (Exception e) {
            DialogsUtils.errorDialog(e.getMessage());
        }
        return null;
    }

    public static FXMLLoader getLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(FxmlUtils.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        return loader;
    }


    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("bundles.messages");
    }

}
