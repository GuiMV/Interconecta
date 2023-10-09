package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/base_dados";
    public static final String USER = "root";
    public static final String PWD = "202110040021";
 
    private static Connection conn;
    
    public static Connection getDatabaseConnection(){
        try{
            if (conn == null){
                conn = DriverManager.getConnection(URL, USER, PWD);
                return conn;
            }else{
                return conn;
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public PreparedStatement prepareStatement(String query) {
        return null;
    }
}