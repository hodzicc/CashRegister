package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.EmployeesDAOSQLImpl;
import ba.unsa.etf.rpr.business.EmployeesManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX controller for login
 * @author Amna Hodzic
 */
public class LoginController {

    //manager
    private EmployeesManager manager = new EmployeesManager();

    //form components
    public PasswordField PasswordLine;
    public TextField UsrnmLine;
    public Button LoginBtn;

    /**
     * Login button event handler
     * @param actionEvent
     * @throws CashRegisterException
     */

    public void loginClick(ActionEvent actionEvent) throws CashRegisterException {
        String username = new String(UsrnmLine.getText());
        String password = new String (PasswordLine.getText());

        Employees empl = manager.getByUsername(username);

        if(empl==null)
        {
            new Alert(Alert.AlertType.ERROR, "Invalid username", ButtonType.OK).show();

        }

        if(empl.isAdmin() && empl.getPassword().equals(password)){
            openDialog("AdminMenu", "/fxml/AdminMenu.fxml",null);

        }
        else if(!empl.isAdmin() && empl.getPassword().equals(password))
        {
            openDialog("Receipts", "/fxml/MakeReceipt.fxml",null);
        }
        else{
            new Alert(Alert.AlertType.ERROR, "Invalid password", ButtonType.OK).show();

        }


    }

    /**
     * private method for opening a new dialog
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
            stage.setResizable(false);
            stage.show();
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }

}
