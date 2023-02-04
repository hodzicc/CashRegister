package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.DAO.ProductsDAOSQLImpl;
import ba.unsa.etf.rpr.business.ProductsManager;
import ba.unsa.etf.rpr.domain.Products;
import ba.unsa.etf.rpr.exceptions.CashRegisterException;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/***
 * JavaFX controller class for update
 * @author Amna Hodzic
 */
public class UpdateController {

    //manager
    private ProductsManager manager = new ProductsManager();
    private Integer id;

    //form components
    public Label idLabel;
    public Label nameLabel;
    public TextField priceField;
    public TextField lisField;
    public Button saveBtn;
    public Button cancelBtn;

    //constructor method for initializing id attribute
     UpdateController(Integer id){

        this.id=id;

    }


    /**
     * initialize method for putting data from db to required fields
     * @throws CashRegisterException
     */
    public void initialize() throws CashRegisterException {
        Products prod = new Products();

        prod = manager.getById(id);
        idLabel.setText(String.valueOf(prod.getId()));
        nameLabel.setText(prod.getName());
        priceField.setText(String.valueOf(prod.getPrice()));
        lisField.setText(String.valueOf(prod.getLeftInStock()));
    }


    /**
     * save button event handler
     * saves changes made to the product
     * @param mouseEvent
     * @throws CashRegisterException
     */
    public void onSaveClicked(MouseEvent mouseEvent) throws CashRegisterException {
        if(priceField.getText().trim().isEmpty() || lisField.getText().trim().isEmpty())
        {
            new Alert(Alert.AlertType.ERROR,"Please enter valid data", ButtonType.OK).show();
        }
        else {
            try {
                String name = nameLabel.getText();
                Double price;
                int left;
                try {
                    price = Double.valueOf(priceField.getText());
                    left = Integer.parseInt(lisField.getText());
                }
                catch(Exception e)
                {
                    throw new CashRegisterException("Incorrect number format");
                }
                Products prod = new Products();
                prod.setId(id);
                prod.setName(name);
                prod.setPrice(price);
                prod.setLeftInStock(left);

                manager.update(prod);
                closeDialog(mouseEvent);
                new Alert(Alert.AlertType.NONE, "Product updated successfully", ButtonType.OK).show();
            }
            catch(CashRegisterException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.OK).show();
            }

        }

    }


    /**
     * cancel button event handler
     * @param mouseEvent
     */
    public void onCancelClicked(MouseEvent mouseEvent) {
         closeDialog(mouseEvent);
    }

    /**
     * method for closing the dialog
     * @param mouseEvent
     */

    private void closeDialog(MouseEvent mouseEvent){
        Node n = (Node) mouseEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
