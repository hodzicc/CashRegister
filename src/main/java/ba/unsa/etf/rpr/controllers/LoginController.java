package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.EmployeesDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Employees;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class LoginController {
    public PasswordField PasswordLine;
    public TextField UsrnmLine;
    public Button LoginBtn;
    public void loginClick(ActionEvent actionEvent) {
        String username = new String(UsrnmLine.getText());
        String password = new String (PasswordLine.getText());

        EmployeesDAOSQLImpl sqlimpl = new EmployeesDAOSQLImpl();
        Employees empl = sqlimpl.getByUsername(username);

        if(empl==null)
        {
            new Alert(Alert.AlertType.ERROR, "Pogrešan username ili password", ButtonType.OK).show();

        }

        if(empl.isAdmin()){

        }
        else{

        }


    }
}
