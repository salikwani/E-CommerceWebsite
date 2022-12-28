package com.example.ecommercewebsite;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class Order {
    void placeOrder(String productID) throws SQLException {
        ResultSet res = HelloApplication.connection.exeQuery("Select max(orderID) as orderID from orders");
        int orderID = 1;
        if(res.next()){
            orderID = res.getInt("orderID")+1;
        }
        Timestamp datetime = new Timestamp(Calendar.getInstance().getTime().getTime());
        String query = String.format("Insert into orders values(%s,%s,'%s','%s')",
                orderID,productID,HelloApplication.emailID,datetime);
        int response = HelloApplication.connection.exeUpdate(query);
        if(response>0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType buttonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonType);
            dialog.setContentText("Your order is placed!");
            dialog.showAndWait();
        }
    }
}
