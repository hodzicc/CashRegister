package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    }

    public void onSaveClicked(MouseEvent mouseEvent) {
    }
}
