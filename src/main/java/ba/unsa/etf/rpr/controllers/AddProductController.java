package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * JavaFX controller for adding new products
 *
 * @author Amna Hodzic
 */

public class AddProductController {

    //variable for data validation
    private boolean ok=true;

    //manager
    private ProductsManager manager = new ProductsManager();

    //form components
    public Button saveBtn;
    public Button cancelBtn;
    public TextField nameField;
    public TextField priceField;
    public TextField leftField;
    public Label nameCheck;

    //initialize method with listeners
    public void initialize() throws CashRegisterException {
        

        List<Products> prod = new ArrayList<>();
        prod=manager.getAll();
        List<Products> finalProd = prod;
        
        nameField.textProperty().addListener((obs, oldValue, newValue)-> {
            if(newValue.matches("^[a-zA-Z]")) {
                ok = false;
                nameCheck.setText("Please only use letters a-z, A-Z, between 3 and 45 characters");
            } else {
                for (Products p : finalProd) {

                    if (p.getName().equals(newValue)) {
                        nameCheck.setText("That product already exists in the table");
                        ok = false;
                        break;
                    } else {
                        nameCheck.setText("");
                        ok = true;
                    }
                }
            }
        });
        
    }

    /**
     * method for Dialog closing
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
     * @param mouseEvent
     * @throws CashRegisterException
     */
    public void onSaveClicked(MouseEvent mouseEvent) throws CashRegisterException {
        if (!ok || nameField.getText().trim().isEmpty() ||
                priceField.getText().trim().isEmpty()
                || leftField.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid data", ButtonType.OK).show();
        } else {
            try {
                String name = nameField.getText();
                Double price;
                int left;
               try {
                   price = Double.valueOf(priceField.getText());
                   left = Integer.parseInt(leftField.getText());
               }
               catch(Exception e)
               {
                   throw new CashRegisterException("Incorrect number format");
               }
                    Products prod = new Products();
                    prod.setName(name);
                    prod.setPrice(price);
                    prod.setLeftInStock(left);

                    manager.add(prod);

                    new Alert(Alert.AlertType.NONE, "New product added successfully", ButtonType.OK).show();

                    closeDialog(mouseEvent);

            } catch (CashRegisterException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
            }
        }
    }
}
