module com.example.ecommercewebsite {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ecommercewebsite to javafx.fxml;
    exports com.example.ecommercewebsite;
}