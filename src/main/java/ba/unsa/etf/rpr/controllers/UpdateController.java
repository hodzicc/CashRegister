package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateController {
    private int id;

    public Label idLabel;
    public Label nameLabel;
    public TextField priceField;
    public TextField lisField;
    public Button saveBtn;
    public Button cancelBtn;

    UpdateController(int id){

        this.id=id;

    }

    public void initialize(){

        Products prod = new Products();
        ProductsDAOSQLImpl sqlimpl = new ProductsDAOSQLImpl();
        prod = sqlimpl.getById(id);
        idLabel.setText(String.valueOf(prod.getId()));
        nameLabel.setText(prod.getName());
        priceField.setText(String.valueOf(prod.getPrice()));
        lisField.setText(String.valueOf(prod.getLeftInStock()));
    }


}
