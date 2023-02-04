package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for AdminMenu
 * @author Amna Hodzic
 */

public class AdminMenuController {

    /**
     * method for Dialog closing
     * @param actionEvent
     */
    private void closeDialog(ActionEvent actionEvent){
        Node n = (Node)actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * Products button event handler
     * @param actionEvent
     */
    public void onProductsClicked(ActionEvent actionEvent) {

        openDialog("Products", "/fxml/Products.fxml",null);
        closeDialog(actionEvent);

    }

    /**
     * Receipts button event handler
     * @param actionEvent
     */
    public void onReceiptsClicked(ActionEvent actionEvent) {

        openDialog("Receipts", "/fxml/MakeReceipt.fxml",null);
        closeDialog(actionEvent);

    }

    /**
     * Registration button event handler
     * @param actionEvent
     */
    public void onUserRegistrationClicked(ActionEvent actionEvent) {
        openDialog("User Registration", "/fxml/UserRegistration.fxml",null);
        closeDialog(actionEvent);

    }

    /**
     * method for opening new Dialog
     * @param title
     * @param file
     * @param controller
     */

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
