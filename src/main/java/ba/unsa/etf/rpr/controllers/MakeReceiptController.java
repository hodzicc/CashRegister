package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MakeReceiptController {

    private int id;
    public Button exitBtn;
    public Button makeBtn;
    public Button addBtn;
    public Label checkQuantityLabel;
    public TextField quantityField;
    public Label checkIdLabel;
    public TextField idField;
    public VBox quantityCol;
    public TableColumn totalCol;
    public TableColumn unitPriceCol;
    public TableColumn nameCol;
    public TableView productsTable;
    public void initialize(){


        ProductsDAOSQLImpl sqlimpl = new ProductsDAOSQLImpl();
        List<Products> prod = new ArrayList<>();
        prod = sqlimpl.getAll();

        List<Products> finalProd= prod;

        idField.textProperty().addListener((obs, oldValue, newValue)->{
        int ok = 0;

            for(Products p: finalProd) {

                if (p.getId()==Integer.parseInt(newValue)) {
                    ok=1;
                   break;
                }

            }
            if(ok==1)
            checkIdLabel.setText("");
            else checkIdLabel.setText("There is no product with that id");

        });





    }

    public void onAddClicked(ActionEvent actionEvent) {
    }

    public void onMakeClicked(ActionEvent actionEvent) {
    }

    public void onExitClicked(ActionEvent actionEvent) {
    }
}
