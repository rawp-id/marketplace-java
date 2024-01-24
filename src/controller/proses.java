/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.proses to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.bayar;
import view.home;
import view.profile;
import model.user;

/**
 *
 * @author lenovo
 */
public class proses extends user {

    public Statement st;
    public ResultSet rs;
    public PreparedStatement pst = null;
    public DefaultTableModel tabModel;
    Connection cn = Koneksi.Koneksi();

    public boolean authenticateUser(String username, String password) {
        try {
            st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM `customers` WHERE username = '" + username + "' AND password = '" + password + "'");
            if (rs.next()) {
                rs.close();
                st.close();
                cn.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getIdDb(String username, String password) {
        String id = null;
        try {
            String sql = "SELECT * FROM `customers` WHERE username = ? AND password = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getString("customer_id");
                setId(Integer.parseInt(id));
                System.out.println(id);
            }
            rs.last();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCart(String produk, String jumlah) {
        try {
            String sql = "INSERT INTO `cart_items`(`cart_id`, `product_id`, `quantity`) VALUES ('" + Integer.toString(getId()) + "','" + produk + "','" + jumlah + "')";
            pst = cn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Berhasil Menambahkan");
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal Menambahkan");
        }
    }

//    public void showCart(String where) {
//        try {
//            st = cn.createStatement();
//            tabModel.getDataVector().removeAllElements();
//            tabModel.fireTableDataChanged();
//            rs = st.executeQuery("SELECT products.name, products.price, cart_items.quantity, products.price*cart_items.quantity as jumlah "
//                    + "FROM cart_items "
//                    + "JOIN shopping_cart ON cart_items.cart_id = shopping_cart.cart_id "
//                    + "JOIN products ON cart_items.product_id = products.product_id "
//                    + "WHERE " + where);
//
//            while (rs.next()) {
//                Object[] data = {
//                    rs.getString("name"),
//                    rs.getString("price"),
//                    rs.getString("quantity"),
//                    rs.getString("jumlah"),};
//
//                tabModel.addRow(data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void showBuy(String where) {
//        try {
//            st = cn.createStatement();
//            tabModel.getDataVector().removeAllElements();
//            tabModel.fireTableDataChanged();
//            rs = st.executeQuery("SELECT products.name, products.price, orders.order_date "
//                    + "FROM order_items "
//                    + "JOIN orders ON order_items.order_id = orders.order_id "
//                    + "JOIN products ON order_items.product_id = products.product_id "
//                    + "WHERE " + where);
//
//            while (rs.next()) {
//                Object[] data = {
//                    rs.getString("name"),
//                    rs.getString("price"),
//                    rs.getString("order_date"),};
//
//                tabModel.addRow(data);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void buy(String id) {
        try {
            st = cn.createStatement();
            st.executeUpdate("INSERT INTO orders (order_id, customer_id, order_date) "
                    + "SELECT shopping_cart.cart_id, shopping_cart.customer_id, CURRENT_TIMESTAMP "
                    + "FROM shopping_cart "
                    + "WHERE shopping_cart.customer_id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = cn.createStatement();
            st.executeUpdate("INSERT INTO `order_items`(`item_id`, `order_id`, `product_id`, `quantity`) "
                    + "SELECT cart_items.item_id, cart_items.cart_id, cart_items.product_id, cart_items.quantity "
                    + "FROM cart_items "
                    + "WHERE cart_items.cart_id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st = cn.createStatement();
            st.executeUpdate("DELETE FROM `cart_items` WHERE cart_id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
