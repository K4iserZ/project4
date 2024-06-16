package com.test.bd;
import java.sql.*;

public class Conexion {
    public static Connection getCon(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/panquita?useSSL=false","root","Mina");
            return con;
        }
        catch(Exception e){
            return null;   
        }   
    }   
}