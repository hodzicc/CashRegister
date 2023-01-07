package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UpdateController {

    private ProductsManager manager = new ProductsManager();
    private Integer id;

    public Label idLabel;
    public Label nameLabel;
    public TextField priceField;
    public TextField lisField;
    public Button saveBtn;
    public Button cancelBtn;

     UpdateController(Integer id){

        this.id=id;

    }

    public void initialize() throws CashRegisterException {
        Products prod = new Products();

        prod = manager.getById(id);
        idLabel.setText(String.valueOf(prod.getId()));
        nameLabel.setText(prod.getName());
        priceField.setText(String.valueOf(prod.getPrice()));
        lisField.setText(String.valueOf(prod.getLeftInStock()));
    }



    public void onSaveClicked(MouseEvent mouseEvent) throws CashRegisterException {

        String name = nameLabel.getText();
        Double price = Double.valueOf(priceField.getText());
        int left = Integer.parseInt(lisField.getText());

        Products prod = new Products();
        prod.setId(id);
        prod.setName(name);
        prod.setPrice(price);
        prod.setLeftInStock(left);

        manager.update(prod);


        closeDialog(mouseEvent);

        new Alert(Alert.AlertType.NONE,"Product updated successfully", ButtonType.OK).show();

    }


    public void onCancelClicked(MouseEvent mouseEvent) {
        closeDialog(mouseEvent);
    }

    private void closeDialog(MouseEvent mouseEvent){
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
