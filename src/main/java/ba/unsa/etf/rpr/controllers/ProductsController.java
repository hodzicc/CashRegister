package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class ProductsController {
    public Button addBtn;
    public Button UpdateBtn;
    public Button DeleteBtn;
    public Button ExitBtn;
    public TableView productsTable;
    public TableColumn<Products,Integer> ProductIdCol;
    public TableColumn<Products, String> ProductNameCol;
    public TableColumn<Products, Double> ProductPriceCol;
    public TableColumn<Products, Integer> LeftInStockCol;

    public void initialize(){


        ProductIdCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        ProductNameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        ProductPriceCol.setCellValueFactory(new PropertyValueFactory<Products, Double>("price"));
        LeftInStockCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("leftInStock"));

        ProductsDAOSQLImpl products = new ProductsDAOSQLImpl();


        productsTable.setItems(FXCollections.observableList(products.getAll()));
        productsTable.refresh();

    }


    public void onAddClicked(MouseEvent mouseEvent) {

    }

    public void onUpdateClicked(MouseEvent mouseEvent) {
    }

    public void onDeleteClicked(MouseEvent mouseEvent) {
    }

    public void onExitClicked(MouseEvent mouseEvent) {
    }

    private void openDialog(String title, String file, Object controller){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
            loader.setController(controller);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setTitle(title);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setResizable(false);
        }catch (Exception e){
            new Alert(Alert.AlertType.NONE, e.getMessage(), ButtonType.OK).show();
        }
    }
}

