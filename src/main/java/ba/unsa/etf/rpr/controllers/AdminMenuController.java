package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AdminMenuController {


    public void onProductsClicked(ActionEvent actionEvent) {

        openDialog("Products", "/fxml/Products.fxml",null);

    }

    public void onReceiptsClicked(ActionEvent actionEvent) {

    }

    public void onUserRegistrationClicked(MouseEvent mouseEvent) {
        openDialog("User Registration", "/fxml/UserRegistration.fxml",null);

    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setResizable(false);
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
