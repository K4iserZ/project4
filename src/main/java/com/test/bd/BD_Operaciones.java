package com.test.bd;

import javax.swing.JOptionPane;
import java.sql.*;

public class BD_Operaciones {
    public static void setDataOrDelete(String Query,String msg){
        try{
            Connection con = Conexion.getCon();
            Statement st = con.createStatement();
            st.executeUpdate(Query);
            if(!msg.equals(""))
            JOptionPane.showMessageDialog(null,msg);
        }
            catch(Exception e){
                    JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
                    }
    }
    
    public static ResultSet getData(String query){
        try{
            Connection con = Conexion.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}