package com.example.ecommercewebsite;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderPage {
    ListView<HBox> orders;
    ListView<HBox> userOrders() throws SQLException {
        orders = new ListView<>();
        ObservableList<HBox> orderList = FXCollections.observableArrayList();
        String query = String.format("Select * from orders where userID='%s'",HelloApplication.emailID);
        ResultSet res = HelloApplication.connection.exeQuery(query);

        Label OrderID = new Label();
        Label ProductID = new Label();
        Label UserID = new Label();
        OrderID.setText("Order ID");
        ProductID.setText("Product ID");
        UserID.setText("UserId");
        OrderID.setMinWidth(120); OrderID.setMinHeight(30);
        ProductID.setMinWidth(120); ProductID.setMinHeight(30);
        UserID.setMinWidth(120); UserID.setMinHeight(30);

        HBox orderHeader = new HBox();
        orderHeader.getChildren().addAll(OrderID,ProductID,UserID);
        orderList.add(orderHeader);

        while(res.next()){
            Label orderID = new Label();
            Label productID = new Label();
            Label userID = new Label();
            orderID.setMinWidth(120);
            productID.setMinWidth(120);
            userID.setMinWidth(120);

            HBox orderDetails = new HBox();
            orderID.setText(res.getString("orderID"));
            productID.setText(res.getString("productID"));
            userID.setText(res.getString("userID"));
            orderDetails.getChildren().addAll(orderID,productID,userID);
            orderList.add(orderDetails);
        }
        orders.setMinWidth(500);
        orders.setItems(orderList);
        return orders;
    }
}
