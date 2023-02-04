package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


/***
 * JavaFX controller for reviewing products from db
 * @author Amna Hodzic
 */

public class ProductsController {

    //manager
    private ProductsManager manager = new ProductsManager();

    //form components
    public Button addBtn;
    public Button UpdateBtn;
    public Button DeleteBtn;
    public Button ExitBtn;
    public TableView productsTable;
    public TableColumn<Products,Integer> ProductIdCol;
    public TableColumn<Products, String> ProductNameCol;
    public TableColumn<Products, Double> ProductPriceCol;
    public TableColumn<Products, Integer> LeftInStockCol;
    public GridPane ProductsPane;

    //initialize method for setting up the tableview
    public void initialize() throws CashRegisterException {


        ProductIdCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<Products, Double>("price"));
        LeftInStockCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("leftInStock"));

        refreshTable();

    }

    /**
     * add button event handler
     * method for opening dialog Addproduct.fxml
     * @param mouseEvent
     */
    public void onAddClicked(MouseEvent mouseEvent) {

        openDialog("Add product", "/fxml/AddProduct.fxml",null);

    }

    /**
     * update button event handler
     * method for opening UpdateProduct dialog and getting the selected id
     * @param mouseEvent
     */

    public void onUpdateClicked(MouseEvent mouseEvent) {
        Products prod=new Products();

        if (productsTable.getSelectionModel().getSelectedItem() != null) {

            prod = (Products) productsTable.getSelectionModel().getSelectedItem();
            UpdateController controller = new UpdateController(prod.getId());
            openDialog("Update product", "/fxml/UpdateProduct.fxml",controller);

        }
        else
            new Alert(Alert.AlertType.ERROR,"You have to select an id", ButtonType.OK).show();

    }

    /**
     * delete button event handler
     * method for deleting selected product from db
     * @param mouseEvent
     * @throws CashRegisterException
     */
    public void onDeleteClicked(MouseEvent mouseEvent) throws CashRegisterException {
        Products prod=new Products();

            if (productsTable.getSelectionModel().getSelectedItem() != null) {
                prod = (Products) productsTable.getSelectionModel().getSelectedItem();
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete");
            Optional<ButtonType> result = confirmation.showAndWait();
            if (!result.get().getButtonData().isCancelButton()){
                manager.delete(prod.getId());

                refreshTable();
            }
            }
            else
                new Alert(Alert.AlertType.ERROR,"You have to select an id", ButtonType.OK).show();

    }

    /**
     * exit button event handler
     * method for closing the dialog
     * @param mouseEvent
     */
    public void onExitClicked(MouseEvent mouseEvent) {
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * method for opening new dialog
     * @param title
     * @param file
     * @param controller
     */

    private void openDialog(String title, String file, Object controller){
        try {
            ((Stage)ProductsPane.getScene().getWindow()).hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setResizable(false);
            stage.setOnHiding(event -> {
                ((Stage)ProductsPane.getScene().getWindow()).show();
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

    /**
     * method for refreshing tableview
     * @throws CashRegisterException
     */
    public void refreshTable() throws CashRegisterException {
        productsTable.setItems(FXCollections.observableList(manager.getAll()));
        productsTable.refresh();
    }
}

