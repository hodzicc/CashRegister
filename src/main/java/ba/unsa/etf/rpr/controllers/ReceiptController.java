package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.Receipt_TotalManager;
import ba.unsa.etf.rpr.business.ReceiptsManager;
import ba.unsa.etf.rpr.domain.Receipt_Total;
import ba.unsa.etf.rpr.domain.Receipts;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;


/***
 * JavaFX controller class for Receipt
 * @author Amna Hodzic
 */
public class ReceiptController {

    //managers
    private ReceiptsManager managerR = new ReceiptsManager();
    private Receipt_TotalManager managerT = new Receipt_TotalManager();
    //form components
    public Button closeBtn;
    public Label dateLabel;
    public Label totalLabel;
    public TableView productsTable;
    public TableColumn nameCol;
    public TableColumn priceCol;
    public TableColumn quantityCol;
    public TableColumn totalCol;

    /**
     * initialize method that puts data from receipts table to tableview
     * @throws CashRegisterException
     */

    public void initialize() throws CashRegisterException {

        nameCol.setCellValueFactory(new PropertyValueFactory<Receipts, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Receipts,Double>("unitPrice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Receipts, Integer>("Quantity"));
        totalCol.setCellValueFactory(new PropertyValueFactory<Receipts, Double>("LineTotal"));

        productsTable.setItems(FXCollections.observableList(managerR.getAll()));
        productsTable.refresh();

        int maxid=1;
        List<Receipt_Total> rec = managerT.getAll();

        for(Receipt_Total r: rec){
            if(r.getId()>maxid)
                maxid=r.getId();
        }
        Receipt_Total totalR = managerT.getById(maxid);

        totalLabel.setText("TOTAL: "+totalR.getTotal());
        dateLabel.setText("DATE: "+totalR.getDate());

    }

    /**
     * method for closing the dialog
     * close button event handler
     * @param mouseEvent
     */
    public void onCloseClicked(MouseEvent mouseEvent) {
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
