package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

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
}

