package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class AddProductController {
    public Button saveBtn;
    public Button cancelBtn;
    public TextField nameField;
    public TextField priceField;
    public TextField leftField;
    public Label nameCheck;

    public void initialize(){
        
        ProductsDAOSQLImpl sqlimpl = new ProductsDAOSQLImpl();
        List<Products> prod = new ArrayList<>();
        prod=sqlimpl.getAll();
        List<Products> finalProd = prod;
        
        nameField.textProperty().addListener((obs, oldValue, newValue)->{

            for(Products p: finalProd) {

                if (p.getName().equals(newValue)) {
                    nameCheck.setText("That product already exists in the table");
                   break;
                }
               else    nameCheck.setText("");
            }
        });
        
    }


    public void onCancelClicked(MouseEvent mouseEvent) {
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void onSaveClicked(MouseEvent mouseEvent) {

         ProductsDAOSQLImpl sqlimpl = new ProductsDAOSQLImpl();
        String name = nameField.getText();
        Double price = Double.valueOf(priceField.getText());
        int left = Integer.parseInt(leftField.getText());

        Products prod = new Products();
        prod.setName(name);
        prod.setPrice(price);
        prod.setLeftInStock(left);

        sqlimpl.add(prod);

        new Alert(Alert.AlertType.NONE,"New product added successfully", ButtonType.OK).show();

        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();


    }
}
