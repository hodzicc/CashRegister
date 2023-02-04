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

public class AddProductController {

    private boolean ok=true;
    private ProductsManager manager = new ProductsManager();
    public Button saveBtn;
    public Button cancelBtn;
    public TextField nameField;
    public TextField priceField;
    public TextField leftField;
    public Label nameCheck;

    public void initialize() throws CashRegisterException {
        

        List<Products> prod = new ArrayList<>();
        prod=manager.getAll();
        List<Products> finalProd = prod;
        
        nameField.textProperty().addListener((obs, oldValue, newValue)-> {
            if(!newValue.matches("[a-zA-Z]+")) {
                ok = false;
                nameCheck.setText("Please only use letters a-z, A-Z");
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

    private void closeDialog(MouseEvent mouseEvent){
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void onCancelClicked(MouseEvent mouseEvent) {
        closeDialog(mouseEvent);
    }

    public void onSaveClicked(MouseEvent mouseEvent) throws CashRegisterException {
        if(!ok || nameField.getText().trim().isEmpty() ||
        priceField.getText().trim().isEmpty()
        || leftField.getText().trim().isEmpty())
        {
            new Alert(Alert.AlertType.ERROR,"Please enter valid data", ButtonType.OK).show();
        }
        else {
            String name = nameField.getText();
            Double price;
            int left;
            try {
                price = Double.valueOf(priceField.getText());
                left = Integer.parseInt(leftField.getText());

            Products prod = new Products();
            prod.setName(name);
            prod.setPrice(price);
            prod.setLeftInStock(left);

            manager.add(prod);

            new Alert(Alert.AlertType.NONE, "New product added successfully", ButtonType.OK).show();

            closeDialog(mouseEvent);
        }
            catch(Exception e){
            new Alert(Alert.AlertType.ERROR,"Please enter valid data", ButtonType.OK).show();
        }
        }
    }
}
