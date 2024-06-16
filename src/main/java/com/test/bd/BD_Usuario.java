package com.test.bd;

import javax.swing.JOptionPane;
import com.test.modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class BD_Usuario {

    public static void save(Usuario user) {
        String query = "insert into user(name, email, mobileNumber, address, password, securityQuestion, answer, status) values('" + user.getName() + "','" + user.getEmail() + "','" + user.getMobileNumber() + "','" + user.getAddress() + "','" + user.getPassword() + "','" + user.getSecurityQuestion() + "','" + user.getAnswer() + "','false')";
        BD_Operaciones.setDataOrDelete(query, "Se ha completado el registro. Espere por la confirmación del administrador.");
    }

    public static Usuario login(String email, String password) {
        Usuario user = null;
        try {
            ResultSet rs = BD_Operaciones.getData("select * from user where email ='" + email + "'and password ='" + password + "'");
            while (rs.next()) {
                user = new Usuario();
                user.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return user;
    }

    public static Usuario getSecurityQuestion(String email) {
        Usuario user = null;
        try {
            ResultSet rs = BD_Operaciones.getData("select * from user where email = '" + email + "'");
            while (rs.next()) {
                user = new Usuario();
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setAnswer(rs.getString("answer"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return user;
    }

    public static void update(String email, String newPassword) {
        String query = "update user set password = '" + newPassword + "' where email = '" + email + "'";
        BD_Operaciones.setDataOrDelete(query, " La contraseña se ha actualizado.");
    }
    
    public static ArrayList<Usuario> getAllRecords(String email){
        ArrayList<Usuario> arrayList = new ArrayList<>();
        try{
            ResultSet rs = BD_Operaciones.getData("select * from user where email like '%"+email+"%'");
            while(rs.next()){
                Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;   
    }
    
    public static void changeStatus(String email,String status){
        String query = "update user set status ='"+status+"' where email ='"+email+"'";
        BD_Operaciones.setDataOrDelete(query," El estado ha sido cambiado.");
        
    }
    
    public static void changePassword(String email,String oldPassword,String newPassword){
        try{
            ResultSet rs = BD_Operaciones.getData("select * from user where email ='"+email+"' and password ='"+oldPassword+"'");
            if(rs.next()){
                update(email, newPassword);
            }
            else{
                
                JOptionPane.showMessageDialog(null,"La contraseña antigua es incorrecta.");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void changeSecurityQuestion(String email,String password,String securityQuestion,String answer){
        try{
            ResultSet rs = BD_Operaciones.getData("select * from user where email='"+email+"' and password='"+password+"'");
            if(rs.next()){
                update(email, securityQuestion, answer);
            }
            else{
                    JOptionPane.showMessageDialog(null,"La contraseña es incorrecta.");
                    }          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void update(String email,String securityQuestion,String answer){
        String query = "update user set securityQuestion='"+securityQuestion+"',answer='"+answer+"' where email='"+email+"'";
        BD_Operaciones.setDataOrDelete(query, "La pregunta de seguridad ha sido cambiada exitosamente.");
    }
}