package com.example.ecommercewebsite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPage {
    ListView<HBox> products;
    ListView<HBox> productsBySearch(String search) throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.exeQuery("Select * from product");
        HBox productHeader = new HBox();
        Label Name = new Label();
        Label ProductID = new Label();
        Label Price = new Label();
        Name.setText("Product Name");
        ProductID.setText("Product ID");
        Price.setText("Price");
        Name.setMinWidth(120); Name.setMinHeight(30);
        ProductID.setMinWidth(120); ProductID.setMinHeight(30);
        Price.setMinWidth(120); Price.setMinHeight(30);
        productHeader.getChildren().addAll(ProductID,Name,Price);
        productList.add(productHeader);

        while(res.next()){
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())){
                Label name = new Label();
                Label productID = new Label();
                Label price = new Label();
                Button buy = new Button();
                name.setMinWidth(120);
                productID.setMinWidth(120);
                price.setMinWidth(120);
                buy.setMinWidth(120);
                buy.setText("Buy");
                HBox productDetails = new HBox();

                buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailID.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Buy");
                            ButtonType buttonType = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(buttonType);
                            dialog.setContentText("Please login first!");
                            dialog.showAndWait();
                        } else {
                            Order order = new Order();
                            try {
                                order.placeOrder(productID.getText());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });

                name.setText(res.getString("productName"));
                productID.setText(res.getString("productID"));
                price.setText(res.getString("price"));
                productDetails.getChildren().addAll(productID, name, price, buy);
                productList.add(productDetails);
            }
        }
        products.setMinWidth(500);
        products.setItems(productList);
        return products;
    }
    ListView<HBox> products() throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.exeQuery("Select * from product");
        HBox productHeader = new HBox();
        Label Name = new Label();
        Label ProductID = new Label();
        Label Price = new Label();
        Name.setText("Product Name");
        ProductID.setText("Product ID");
        Price.setText("Price");
        Name.setMinWidth(120); Name.setMinHeight(30);
        ProductID.setMinWidth(120); ProductID.setMinHeight(30);
        Price.setMinWidth(120); Price.setMinHeight(30);
        productHeader.getChildren().addAll(ProductID,Name,Price);
        productList.add(productHeader);

        while(res.next()){
            Label name = new Label();
            Label productID = new Label();
            Label price = new Label();
            Button buy = new Button();
            name.setMinWidth(120);
            productID.setMinWidth(120);
            price.setMinWidth(120);
            buy.setMinWidth(120);
            buy.setText("Buy");
            HBox productDetails = new HBox();

            buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(HelloApplication.emailID.equals("")) {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Buy");
                        ButtonType buttonType = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(buttonType);
                        dialog.setContentText("Please login first!");
                        dialog.showAndWait();
                    }
                    else {
                        Order order = new Order();
                        try {
                            order.placeOrder(productID.getText());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

            name.setText(res.getString("productName"));
            productID.setText(res.getString("productID"));
            price.setText(res.getString("price"));
            productDetails.getChildren().addAll(productID,name,price,buy);
            productList.add(productDetails);
        }
        products.setMinWidth(500);
        products.setItems(productList);
        return products;
    }
}
