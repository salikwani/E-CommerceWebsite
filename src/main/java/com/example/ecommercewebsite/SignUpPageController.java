package com.example.ecommercewebsite;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class SignUpPageController {
    @FXML
    TextField email,userName;
    @FXML
    PasswordField password;
    @FXML
    public void signup(MouseEvent event){
        String query = String.format("Insert into user values('%s','%s','%s','%s')",
                email.getText(),userName.getText(),password.getText().toString(),"Buyer");
        int response = HelloApplication.connection.exeUpdate(query);
        if(response>0){
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Sign Up");
            ButtonType buttonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonType);
            dialog.setContentText("Your account has been created!");
            dialog.showAndWait();
        }
        else{
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Sign Up");
            ButtonType buttonType = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(buttonType);
            dialog.setContentText("Enter correct details and try again.");
            dialog.showAndWait();
        }
    }
}
