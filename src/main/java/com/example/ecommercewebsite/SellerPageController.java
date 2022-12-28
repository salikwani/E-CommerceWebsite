package com.example.ecommercewebsite;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {
    @FXML
    TextField productName,price;
    @FXML
    public void addProduct(MouseEvent event) throws SQLException {
        int productID = 1;
        ResultSet pID = HelloApplication.connection.exeQuery("Select max(productID) as productID from product");
        if(pID.next()){
            productID = pID.getInt("productID")+1;
        }
        String query = String.format("Insert into product values(%s,'%s',%s,'%s')",productID,productName.getText(),price.getText(),HelloApplication.emailID);
        int response = HelloApplication.connection.exeUpdate(query);
        if(response>0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Product");
            ButtonType buttonType = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonType);
            dialog.setContentText("New product has been added!");
            dialog.showAndWait();
        }
        else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Product");
            ButtonType buttonType = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonType);
            dialog.setContentText("Enter correct details and try again.");
            dialog.showAndWait();
        }
    }
}
