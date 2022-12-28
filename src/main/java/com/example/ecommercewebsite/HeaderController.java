package com.example.ecommercewebsite;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class HeaderController {
    @FXML
    public void initialize(){
        if(!HelloApplication.emailID.equals("")){
            logoutButton.setOpacity(1);
            loginButton.setOpacity(0);
            signupButton.setOpacity(0);
            email.setMinWidth(220);
            email.setMinHeight(50);
            email.setText(HelloApplication.emailID);
        }
        else{
            logoutButton.setOpacity(0);
        }
    }
    @FXML
    Button loginButton,logoutButton,signupButton;
    @FXML
    Label email;
    @FXML
    TextField searchText;
    @FXML
    public void login(MouseEvent event) throws IOException {
        AnchorPane LoginPage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        LoginPage.setLayoutY(50);
        Header header = new Header();
        HelloApplication.root.getChildren().addAll(header.root,LoginPage);
    }
    @FXML
    public void signup(MouseEvent event) throws IOException {
        AnchorPane SignUpPage = FXMLLoader.load(getClass().getResource("SignUpPage.fxml"));
        SignUpPage.setLayoutY(50);
        Header header = new Header();
        HelloApplication.root.getChildren().addAll(header.root,SignUpPage);
    }
    @FXML

    public void search(MouseEvent event) throws IOException, SQLException {
        Header header = new Header();
        ProductPage productPage = new ProductPage();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.productsBySearch(searchText.getText()));
        productPane.setLayoutY(50);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(productPane,header.root);
    }
    @FXML
    public void logout(MouseEvent event) throws SQLException, IOException {
        if(!HelloApplication.emailID.equals("")){
            HelloApplication.emailID = "";
            Header header = new Header();
            ProductPage productPage = new ProductPage();
            AnchorPane productPane = new AnchorPane();
            productPane.getChildren().add(productPage.products());
            productPane.setLayoutY(50);
            HelloApplication.root.getChildren().clear();
            HelloApplication.root.getChildren().addAll(productPane,header.root);
        }
    }
    @FXML
    public void order(MouseEvent event) throws SQLException, IOException {
        if(!HelloApplication.emailID.equals("")){
            Header header = new Header();
            OrderPage orderPage = new OrderPage();
            AnchorPane orderPane = new AnchorPane();
            orderPane.getChildren().add(orderPage.userOrders());
            orderPane.setLayoutY(50);
            HelloApplication.root.getChildren().clear();
            HelloApplication.root.getChildren().addAll(orderPane,header.root);
        }
    }
}
