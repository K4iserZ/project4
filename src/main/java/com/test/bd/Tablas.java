package com.test.bd;

import javax.swing.JOptionPane;

public class Tablas {

    public static void main(String[] args) {
        try {
            String userTable = "create table user(id int AUTO_INCREMENT primary key, name varchar(200),email varchar(200),mobileNumber varchar(9),address varchar(200),password varchar(200),securityQuestion varchar(200),answer varchar(200),status varchar(20),UNIQUE(email))";
            //String adminDetails = "insert into user(name,email,mobileNumber,address,password,securityQuestion,answer,status) values('Admin','admin@gmail.com','123456789','Ica','admin','¿Cuál es el nombre de tu primera mascota?','Dakota','true')";
            //String categoryTable = "create table category (id int AUTO_INCREMENT primary key,name varchar(200))";
            //String productTable = "create table product(id int AUTO_INCREMENT primary key,name varchar(200),category varchar(200),price varchar(200))";
            //String billTable = "create table bill(id int primary key,name varchar(200),mobileNumber varchar(200),email varchar(200),date varchar(50),total varchar(200),createdBy varchar(200))";
            BD_Operaciones.setDataOrDelete(userTable, "La tabla usuario ha sido creada.");
            //BD_Operaciones.setDataOrDelete(adminDetails, "Se añadieron los detalles del administrador.");
            //BD_Operaciones.setDataOrDelete(categoryTable, "La tabla categoría ha sido creada.");
            //BD_Operaciones.setDataOrDelete(productTable, "La tabla producto ha sido creada");
            //BD_Operaciones.setDataOrDelete(billTable, "La tabla boleta ha sido creada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}