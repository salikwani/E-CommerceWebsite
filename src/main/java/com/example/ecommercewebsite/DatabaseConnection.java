package com.example.ecommercewebsite;
import java.sql.*;

public class DatabaseConnection {
    Connection con = null;
    String SQLURL = "jdbc:mysql://localhost:3306/ecommerce";
    String userName = "root";
    String password = "server4PROJECTS@r00t";
    DatabaseConnection() throws SQLException {
        con = DriverManager.getConnection(SQLURL,userName,password);
    }
    public ResultSet exeQuery(String query){
        ResultSet result = null;
        try{
            Statement statement = con.createStatement();
            result = statement.executeQuery(query);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int exeUpdate(String query){
        int row = 0;
        try{
            Statement statement = con.createStatement();
            row = statement.executeUpdate(query);
            return row;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return row;
    }
}
