package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
public class AppFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        stage.setTitle("Cash Register");
        stage.setScene(new Scene(root, 300, 275));
        stage.setResizable(false);
        stage.show();

    }
}
