package com.test.bd;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import com.test.modelo.Boleta;

public class BD_Boleta {

    public static String getId() {
        int id = 1;
        try {
            ResultSet rs = BD_Operaciones.getData("select max(id) from bill");
            if (rs.next()) {
                id = rs.getInt(1);
                id = id + 1;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return String.valueOf(id);
    }
    
    public static void save(Boleta bill){
        String query = "insert into bill values('"+bill.getId()+"','"+bill.getName()+"','"+bill.getMobileNumber()+"','"+bill.getEmail()+"','"+bill.getDate()+"','"+bill.getTotal()+"','"+bill.getCreatedBy()+"')";
        BD_Operaciones.setDataOrDelete(query, "Detalles de la boleta añadidos exitosamente");
        
    }
    
    public static ArrayList<Boleta> getAllRecordsByInc(String date){
        ArrayList<Boleta> arrayList = new ArrayList<>();
        try{
            ResultSet rs = BD_Operaciones.getData("Select * from bill where date like '%"+date+"%'");
            while(rs.next()){
                Boleta bill = new Boleta();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setMobileNumber(rs.getString("mobileNumber"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("date"));
                bill.setTotal(rs.getString("total"));
                bill.setCreatedBy(rs.getString("createdBy"));
                arrayList.add(bill);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return arrayList;
    }
    
    public static ArrayList<Boleta> getAllRecordsByDesc(String date){
        ArrayList<Boleta> arrayList = new ArrayList<>();
        try{
            ResultSet rs = BD_Operaciones.getData("Select * from bill where date like '%"+date+"%' order by id DESC");
            while(rs.next()){
                Boleta bill = new Boleta();
                bill.setId(rs.getInt("id"));
                bill.setName(rs.getString("name"));
                bill.setMobileNumber(rs.getString("mobileNumber"));
                bill.setEmail(rs.getString("email"));
                bill.setDate(rs.getString("date"));
                bill.setTotal(rs.getString("total"));
                bill.setCreatedBy(rs.getString("createdBy"));
                arrayList.add(bill);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return arrayList;
    }
}