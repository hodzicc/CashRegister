package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.domain.Products;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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


    public void onSaveClicked(MouseEvent mouseEvent) {

        ProductsDAOSQLImpl sqlimpl = new ProductsDAOSQLImpl();
        String name = nameLabel.getText();
        Double price = Double.valueOf(priceField.getText());
        int left = Integer.parseInt(lisField.getText());

        Products prod = new Products();
        prod.setId(id);
        prod.setName(name);
        prod.setPrice(price);
        prod.setLeftInStock(left);

        sqlimpl.update(prod);

        new Alert(Alert.AlertType.NONE,"Product updated successfully", ButtonType.OK).show();

        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();


    }

    public void onCancelClicked(MouseEvent mouseEvent) {
    }
}
