package ba.unsa.etf.rpr.controllers;

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

    }



}
