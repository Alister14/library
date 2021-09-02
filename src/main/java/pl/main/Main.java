package pl.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.Data.DatabaseHandling;
import pl.utils.FxmlUtils;

public class Main extends Application {

    public static final String MAIN_PANE_FXML = "/fxml/MainPane.fxml";
    private Stage mainStage;


    public static void main(String[] args) {

        DatabaseHandling dataHandling = new DatabaseHandling();
        DatabaseHandling.ReadDatabase();
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane borderPane = FxmlUtils.fxmlLoader(MAIN_PANE_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtils.getResourceBundle().getString("tittle.application"));
        primaryStage.show();
    }
}
