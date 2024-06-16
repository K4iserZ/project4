package com.test.bd;

import com.test.modelo.Producto;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;

public class BD_Productos {

    public static void save(Producto product) {
        String query = "insert into product (name,category,price) values('" + product.getName() + "','" + product.getCategory() + "','" + product.getPrice() + "') ";
        BD_Operaciones.setDataOrDelete(query, "Nuevo producto añadido exitosamente");

    }

    public static ArrayList<Producto> getAllRecords() {
        ArrayList<Producto> arrayList = new ArrayList<>();
        try {
            ResultSet rs = BD_Operaciones.getData("select * from product");
            while (rs.next()) {
                Producto product = new Producto();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCategory(rs.getString("category"));
                product.setPrice(rs.getString("price"));
                arrayList.add(product);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return arrayList;

    }

    public static void update(Producto product) {
        String query = "update product set name ='" + product.getName() + "',category ='" + product.getCategory() + "', price = '" + product.getPrice() + "' where id = '" + product.getId() + "'";
        BD_Operaciones.setDataOrDelete(query, "Producto actualizado exitosamente");
    }

    public static void delete(String id) {
        String query = "delete from product where id = '" + id + "'";
        BD_Operaciones.setDataOrDelete(query, "El producto ha sido eliminado");

    }

    public static ArrayList<Producto> getAllRecordsByCategory(String category) {
        ArrayList<Producto> arrayList = new ArrayList<>();
        try {
            ResultSet rs = BD_Operaciones.getData("select * from product where category ='" + category + "'");
            while(rs.next()){
                Producto product = new Producto();
                product.setName(rs.getString("name"));
                arrayList.add(product);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        return arrayList;
    }

    public static ArrayList<Producto> filterProductByname(String name, String category) {
        ArrayList<Producto> arrayList = new ArrayList<>();
        try {
            ResultSet rs = BD_Operaciones.getData("select * from product where name like '%" + name + "%' and category = '" + category + "'");
            while(rs.next()){
                Producto product = new Producto();
                product.setName(rs.getString("name"));
                arrayList.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static Producto getProductByname(String name) {
        Producto product = new Producto();
        try {
            ResultSet rs = BD_Operaciones.getData("select * from product where name ='" + name + "'");
            while (rs.next()) {
                product.setName(rs.getString(2));
                product.setCategory(rs.getString(3));
                product.setPrice(rs.getString(4));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return product;
    }
}