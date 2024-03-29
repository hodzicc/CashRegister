package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.DAO.Receipt_TotalDAOSQLImpl;
import ba.unsa.etf.rpr.DAO.ReceiptsDAOSQLImpl;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.business.Receipt_TotalManager;
import ba.unsa.etf.rpr.business.ReceiptsManager;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

/**
 * JavaFX controller class for making receipts
 * @author Amna Hodzic
 */
public class MakeReceiptController {

   //managers
    private ReceiptsManager managerR = new ReceiptsManager();
    private ProductsManager managerP = new ProductsManager();
    private Receipt_TotalManager managerT = new Receipt_TotalManager();

    //form components
    public GridPane grid;
    public TableColumn quantityCol;
    public TableColumn unitPriceCol;
    private int id;
    private int okk=0;
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

    /**
     * initialize method with listeners for data validation
     * @throws CashRegisterException
     */
    public void initialize() throws CashRegisterException {

        List<Receipts> rec = managerR.getAll();
        for(Receipts r: rec){
            managerR.delete(r.getId());
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<Receipts, String>("name"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<Receipts,Double>("unitPrice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("Quantity"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Receipts, Double>("LineTotal"));

        refreshTable();

        id=1;

        idField.textProperty().addListener((obs, oldValue, newValue)->{
            if(!newValue.trim().isEmpty()) {
                Products prod = new Products();
                okk = 1;

                int id;
                try {
                    id = Integer.parseInt(newValue);
                } catch (Exception e) {
                    checkIdLabel.setText("Incorrect number format");
                    okk = 0;
                }
                if (okk == 1)
                    try {
                        prod = managerP.getById(Integer.parseInt(newValue));
                    } catch (CashRegisterException e) {
                        checkIdLabel.setText("There is no product with that id");
                        okk = 0;
                    }

                if (okk == 1) {
                    checkIdLabel.setText("");
                    if (checkQuantityLabel.getText() == "Id not found")
                        checkQuantityLabel.setText("");
                    okk = 1;
                }
            }
        });

        quantityField.textProperty().addListener((obs, oldValue, newValue)->{
            if(!newValue.trim().isEmpty()) {
                okk = 1;

                Products prod = new Products();
                try {
                    prod = managerP.getById(Integer.parseInt(idField.getText()));
                } catch (Exception e) {
                    okk = 0;
                    checkQuantityLabel.setText("Id not found");
                }
                if (okk == 1)
                    if (prod.getLeftInStock() < Integer.parseInt(newValue)) {
                        okk = 0;
                        checkQuantityLabel.setText("There is not enough items left");
                    } else {
                        okk = 1;
                        checkQuantityLabel.setText("");
                    }
            }
        });

    }

    /**
     * add button event handler
     * adds new products to the receipts table
     * validates given data
     * @param actionEvent
     * @throws CashRegisterException
     */
    public void onAddClicked(ActionEvent actionEvent) throws CashRegisterException {

        if (okk == 1) {
            try {

                if (quantityField.getText().trim().isEmpty() || idField.getText().trim().isEmpty())
                    throw new CashRegisterException("Please enter required data");
                Receipts item = new Receipts();
                item.setIdR(id);
                int idp, quan;
                try {
                    idp = Integer.parseInt(idField.getText());
                    quan = Integer.parseInt(quantityField.getText());
                } catch (Exception e) {
                    throw new CashRegisterException("Incorrect data format");
                }
                item.setQuantity(quan);
                item.setIdP(idp);
                Products prod = new Products();
                prod = managerP.getById(idp);

                double d = prod.getPrice() * quan;
                d= Math.round(d*100);
                d=d/100;
                item.setLineTotal(d);
                item.setUnitPrice(prod.getPrice());
                item.setName(prod.getName());


                managerR.add(item);
                refreshTable();
                idField.setText("");
                quantityField.setText("");

                int left = prod.getLeftInStock();
                prod.setLeftInStock(left - quan);
                managerP.update(prod);
                checkQuantityLabel.setText("");
                checkIdLabel.setText("");
            }
            catch(CashRegisterException e){
                new Alert(Alert.AlertType.ERROR, e.getMessage(),ButtonType.OK).show();
            }
        }
        else
            new Alert(Alert.AlertType.ERROR,"You have to input id and quantity", ButtonType.OK).show();

    }

    /***
     * Make button event handler
     * opens new dialog for reviewing the receipt
     * @param actionEvent
     * @throws CashRegisterException
     */
    public void onMakeClicked(ActionEvent actionEvent) throws CashRegisterException {
        try {
         if(managerR.getAll()!=null) {
             Receipt_Total r = new Receipt_Total();
             r.setTotal(managerR.getTotal());
             Calendar calendar = Calendar.getInstance();
             java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
             r.setDate(startDate);
             managerT.add(r);
             openDialog("Receipt", "/fxml/Receipt.fxml", null);
             List<Receipts> rec = managerR.getAll();
             for(Receipts r1: rec){
                 managerR.delete(r1.getId());
             }
             refreshTable();

         }
        }
        catch(CashRegisterException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(),ButtonType.OK).show();
        }

    }

    /**
     * exit button event handler
     * @param actionEvent
     */
    public void onExitClicked(ActionEvent actionEvent) {
       closeDialog(actionEvent);

    }

    /**
     * method for closing the dialog
     * @param actionEvent
     */
    private void closeDialog(ActionEvent actionEvent){
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * method for refresing the tableview with data from db
     * @throws CashRegisterException
     */
    public void refreshTable() throws CashRegisterException {


        productsTable.setItems(FXCollections.observableList(managerR.getAll()));
        productsTable.refresh();


    }

    /**
     * method for opening new dialog
     * @param title
     * @param file
     * @param controller
     */
    private void openDialog(String title, String file, Object controller){
        try {
            ((Stage)grid.getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setResizable(false);
            stage.setOnHiding(event -> {
                ((Stage)grid.getScene().getWindow()).show();
                try {
                    refreshTable();
                } catch (CashRegisterException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}
