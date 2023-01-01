package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.DAO.Receipt_TotalDAOSQLImpl;
import ba.unsa.etf.rpr.DAO.ReceiptsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.domain.Receipts;
import javafx.collections.FXCollections;
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

        Receipt_TotalDAOSQLImpl sql = new Receipt_TotalDAOSQLImpl();
        Receipt_Total r = new Receipt_Total();
        sql.add(r);
        List<Receipt_Total> rec = new ArrayList<>();
        rec=sql.getAll();
        Receipt_Total r1= rec.get(rec.size()-1);
        id=r1.getId();

        idField.textProperty().addListener((obs, oldValue, newValue)->{
            ProductsDAOSQLImpl sqlimpl = new ProductsDAOSQLImpl();
             Products prod = new Products();
            /*
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

             */
            prod= sqlimpl.getById(Integer.parseInt(newValue));
            if(prod==null)
                checkIdLabel.setText("There is no product with that id");
            else  checkIdLabel.setText("");





        });

        quantityField.textProperty().addListener((obs, oldValue, newValue)->{
          ProductsDAOSQLImpl sqlimpl = new ProductsDAOSQLImpl();
           Products prod = new Products();
             prod= sqlimpl.getById(Integer.parseInt(idField.getText()));
             if(prod.getLeftInStock()<Integer.parseInt(newValue))
                 checkQuantityLabel.setText("There is not enough items left");
             else checkQuantityLabel.setText("");

        });







    }

    public void onAddClicked(ActionEvent actionEvent) {
        Receipts item = new Receipts();
        item.setIdR(id);
        int idp = Integer.parseInt(idField.getText());
        item.setIdP(idp);
        int quan = Integer.parseInt(quantityField.getText());
        item.setQuantity(quan);

        Products prod = new Products();
        ProductsDAOSQLImpl s = new ProductsDAOSQLImpl();
        prod = s.getById(idp);
        item.setLineTotal(prod.getPrice()*quan);

        ReceiptsDAOSQLImpl receipts = new ReceiptsDAOSQLImpl();
        receipts.add(item);

    }

    public void onMakeClicked(ActionEvent actionEvent) {
    }

    public void onExitClicked(ActionEvent actionEvent) {
    }

    public void refreshTable(){
        ReceiptsDAOSQLImpl receipts = new ReceiptsDAOSQLImpl();
        productsTable.setItems(FXCollections.observableList(receipts.getAll()));
        productsTable.refresh();
    }
}
