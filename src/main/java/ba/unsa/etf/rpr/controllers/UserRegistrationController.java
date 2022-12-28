package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.EmployeesDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employees;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class UserRegistrationController {

    public TextField UsernameField;
    public javafx.scene.control.PasswordField PasswordField;
    public TextField NameField;
    public Button CancelBtn;
    public Button SaveBtn;
    public Label FullNameCheck;
    public Label UsernameCheck;
    public Label PasswordCheck;

    public void initialize(){

        EmployeesDAOSQLImpl sqlimpl = new EmployeesDAOSQLImpl();
        List<Employees> empl = new ArrayList<>();
        empl = sqlimpl.getAll();

        List<Employees> finalEmpl = empl;
        NameField.textProperty().addListener((obs, oldValue, newValue)->{

            for(Employees e: finalEmpl) {

                if (e.getName().equals(newValue)) {
                    FullNameCheck.setText("That user is already registered");
                   break;
                }
               else    FullNameCheck.setText("");
            }
        });
        UsernameField.textProperty().addListener((obs, oldValue, newValue)->{

            for(Employees e: finalEmpl) {

                if (e.getUsername().equals(newValue)) {
                    UsernameCheck.setText("That username already exists");
                    break;
                }
                else    UsernameCheck.setText("");
            }
        });
        PasswordField.textProperty().addListener((obs, oldValue, newValue)->{

                if (newValue.length()<=4) {
                    PasswordCheck.setText("Password too short");

                }
                else    PasswordCheck.setText("");

        });




    }


    public void onCancelClicked(MouseEvent mouseEvent) {

        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

    }

    public void onSaveClicked(MouseEvent mouseEvent) {
    }
}
