package com.example.ecommercewebsite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    public static DatabaseConnection connection;
    public static Group root;
    public static String emailID;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        connection = new DatabaseConnection();
        root = new Group();
        emailID = "";
        Header header = new Header();
        ProductPage productPage = new ProductPage();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutY(50);
        root.getChildren().addAll(productPane,header.root);
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("E-Commerce Website");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(e->{
            try{
                connection.con.close();
                System.out.println("Connection is closed successfully");
            } catch(SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}