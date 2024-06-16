package com.test.bd;

import java.util.ArrayList;
import com.test.modelo.Categoria;
import java.sql.*;
import javax.swing.JOptionPane;

public class BD_Categoria {
    public static void save(Categoria category){
        String query = "insert into category (name) Values ('"+category.getName()+"')";
        BD_Operaciones.setDataOrDelete(query, "Categoría añadida exitosamente");
    }
    
    public static ArrayList<Categoria> getAllRecorded(){
        ArrayList<Categoria> arrayList = new ArrayList<>();
        try{
            ResultSet rs = BD_Operaciones.getData("select * from category");
            while(rs.next()){
                Categoria category = new Categoria();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                arrayList.add(category);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);    
        }
        return arrayList; 
    }
    
    public static void delete(String id){
        String query = "delete from category where id ='"+id+"' ";
        BD_Operaciones.setDataOrDelete(query, "Categoría eliminada exitosamente");    
    }  
}