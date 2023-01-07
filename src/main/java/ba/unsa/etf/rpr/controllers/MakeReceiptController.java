package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.DAO.Receipt_TotalDAOSQLImpl;
import ba.unsa.etf.rpr.DAO.ReceiptsDAOSQLImpl;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.business.ReceiptsManager;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MakeReceiptController {

    private ReceiptsManager managerR = new ReceiptsManager();
    private ProductsManager managerP = new ProductsManager();

    public TableColumn quantityCol;
    public TableColumn unitPriceCol;
    private int id;

    private int employee;
    private int okk;
    public Button exitBtn;
    public Button makeBtn;
    public Button addBtn;
    public Label checkQuantityLabel;
    public TextField quantityField;
    public Label checkIdLabel;
    public TextField idField;

    public TableColumn totalCol;

    public TableColumn nameCol;
    public TableView productsTable;


    public void initialize() throws CashRegisterException {

        managerR.delete(1);

        nameCol.setCellValueFactory(new PropertyValueFactory<Receipts, String>("name"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<Receipts,Double>("unitPrice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("Quantity"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Receipts, Double>("LineTotal"));

        refreshTable();

        id=1;

        idField.textProperty().addListener((obs, oldValue, newValue)->{

             Products prod = new Products();

            try {
                prod= managerP.getById(Integer.parseInt(newValue));
            } catch (CashRegisterException e) {
                throw new RuntimeException(e);
            }
            if(prod==null){
                checkIdLabel.setText("There is no product with that id");
                okk=0;
            }
            else  {
                checkIdLabel.setText("");
                okk=1;
            }

        });

        quantityField.textProperty().addListener((obs, oldValue, newValue)->{

           Products prod = new Products();
            try {
                prod= managerP.getById(Integer.parseInt(idField.getText()));
            } catch (CashRegisterException e) {
                throw new RuntimeException(e);
            }
            if(prod.getLeftInStock()<Integer.parseInt(newValue)) {
                 okk=0;
                 checkQuantityLabel.setText("There is not enough items left");
             }
             else
             {
                 okk=1;
                 checkQuantityLabel.setText("");
             }

        });

    }

    public void onAddClicked(ActionEvent actionEvent) throws CashRegisterException {

        if (okk == 1) {

            Receipts item = new Receipts();
            item.setIdR(id);
            int idp = Integer.parseInt(idField.getText());
            item.setIdP(idp);
            int quan = Integer.parseInt(quantityField.getText());
            item.setQuantity(quan);

            Products prod = new Products();
            prod = managerP.getById(idp);
            item.setLineTotal(prod.getPrice() * quan);

            item.setUnitPrice(prod.getPrice());
            item.setName(prod.getName());


            managerR.add(item);
            refreshTable();
            idField.setText("");
            quantityField.setText("");

            int left = prod.getLeftInStock();
            prod.setLeftInStock(left-quan);
            managerP.update(prod);
        }
        else
            new Alert(Alert.AlertType.ERROR,"You have to input id and quantity", ButtonType.OK).show();

    }

    public void onMakeClicked(ActionEvent actionEvent) {

        

    }

    public void onExitClicked(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

    }

    public void refreshTable() throws CashRegisterException {


        productsTable.setItems(FXCollections.observableList(managerR.getAll()));
        productsTable.refresh();


    }
}
