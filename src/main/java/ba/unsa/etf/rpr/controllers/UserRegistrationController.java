package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.EmployeesDAOSQLImpl;
import ba.unsa.etf.rpr.business.EmployeesManager;
import ba.unsa.etf.rpr.domain.Employees;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * JavaFX controller class for User registration
 */
public class UserRegistrationController {

    //boolean variable for validation
    private boolean ok=true;

    //manager
    private EmployeesManager manager = new EmployeesManager();

    //form components
    public TextField UsernameField;
    public javafx.scene.control.PasswordField PasswordField;
    public TextField NameField;
    public Button CancelBtn;
    public Button SaveBtn;
    public Label FullNameCheck;
    public Label UsernameCheck;
    public Label PasswordCheck;
    public CheckBox checkBoxAdmin;


    /**
     * initialize method for new data validation
     * @throws CashRegisterException
     */
    public void initialize() throws CashRegisterException {


        List<Employees> empl = new ArrayList<>();
        empl = manager.getAll();

        List<Employees> finalEmpl = empl;
        NameField.textProperty().addListener((obs, oldValue, newValue)->{
            if(newValue.matches("^[a-zA-Z]")) {
                ok = false;
                FullNameCheck.setText("Please only use letters a-z, A-Z");
            }
            else
            for(Employees e: finalEmpl) {

                if (e.getName().equals(newValue)) {
                    FullNameCheck.setText("That user is already registered");
                    ok = false;
                   break;
                }
               else {
                    FullNameCheck.setText("");
                    ok=true;
                }
            }
        });
        UsernameField.textProperty().addListener((obs, oldValue, newValue)->{
            if(newValue.matches("^[a-zA-Z]")) {
                ok = false;
                UsernameCheck.setText("Please only use letters a-z, A-Z");
            }
           else
            for(Employees e: finalEmpl) {

                if (e.getUsername().equals(newValue)) {
                    UsernameCheck.setText("That username already exists");
                    ok=false;
                    break;
                }
                else {
                    UsernameCheck.setText("");
                    ok=true;
                }
            }
        });
        PasswordField.textProperty().addListener((obs, oldValue, newValue)->{

                if (newValue.length()<=4) {
                    PasswordCheck.setText("Password too short");
                    ok=false;

                }
                else {
                    PasswordCheck.setText("");
                    ok=true;
                }

        });




    }

    /**
     * method for closing the dialog
     * @param mouseEvent
     */

    private void closeDialog(MouseEvent mouseEvent){
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * cancel button event handler
     * @param mouseEvent
     */
    public void onCancelClicked(MouseEvent mouseEvent) {

       closeDialog(mouseEvent);

    }

    /**
     * save button event handler
     * adds new employee to the db
     * @param mouseEvent
     * @throws CashRegisterException
     */
    public void onSaveClicked(MouseEvent mouseEvent) throws CashRegisterException {
        try {
            String username = UsernameField.getText();
            String password = PasswordField.getText();
            String name = NameField.getText();
            boolean isAdmin = checkBoxAdmin.isSelected();
            if (username.trim().isEmpty() || password.trim().isEmpty() || name.trim().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Invalid registration, please enter required information again", ButtonType.OK).show();
            } else {


                Employees empl = new Employees();
                empl.setName(name);
                empl.setUsername(username);
                empl.setPassword(password);
                empl.setAdmin(isAdmin);

                manager.add(empl);

                new Alert(Alert.AlertType.NONE, "New employee added successfully", ButtonType.OK).show();

                closeDialog(mouseEvent);
            }
        }
        catch(CashRegisterException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }

    }
}
