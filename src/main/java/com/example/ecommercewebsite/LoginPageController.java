package com.example.ecommercewebsite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent event) throws SQLException, IOException {
        String query = String.format("Select * from user where emailID = '%s' and passkey = '%s'",email.getText(),password.getText());
        ResultSet res = HelloApplication.connection.exeQuery(query);
        if(res.next()){
            HelloApplication.emailID = res.getString("emailID");
            String userType = res.getString("userType");
            if(userType.equals("Seller")){
                AnchorPane SellerPage = FXMLLoader.load(getClass().getResource("SellerPage.fxml"));
                Header header = new Header();
                HelloApplication.root.getChildren().addAll(SellerPage,header.root);
            }
            else{
                Header header = new Header();
                ProductPage productPage = new ProductPage();
                AnchorPane productPane = new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setLayoutY(50);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(productPane,header.root);
            }
        }
        else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType buttonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonType);
            dialog.setContentText("Login Failed! Please check email/password and try again.");
            dialog.showAndWait();
        }
    }

}
