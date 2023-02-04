package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class AdminMenuController {

    private void closeDialog(ActionEvent mouseEvent){
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
    public void onProductsClicked(ActionEvent actionEvent) {

        openDialog("Products", "/fxml/Products.fxml",null);
        closeDialog(actionEvent);

    }

    public void onReceiptsClicked(ActionEvent actionEvent) {

        openDialog("Receipts", "/fxml/MakeReceipt.fxml",null);
        closeDialog(actionEvent);

    }

    public void onUserRegistrationClicked(ActionEvent mouseEvent) {
        openDialog("User Registration", "/fxml/UserRegistration.fxml",null);
        closeDialog(mouseEvent);

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
