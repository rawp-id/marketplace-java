/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddMahasiswa {

    public static void main(String[] args) {
        Statement st;
        ResultSet rs;
        PreparedStatement pst = null;
        Connection cn = Koneksi.Koneksi();
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIM : ");
        String nim = scanner.nextLine();
        System.out.print("Nama : ");
        String nama = scanner.nextLine();

        try {
            String sql = "INSERT INTO `mahasiswa`(`nim`, `nama`) VALUES ('" + nim + "','" + nama + "')";
            pst = cn.prepareStatement(sql);
            pst.executeUpdate();
            System.out.println("berhasil menambahkan");
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("gagal menambahkan");
        }
    }

}
