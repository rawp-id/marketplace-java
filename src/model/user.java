/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package model;

/**
 *
 * @author lenovo
 */
public class user{
    private static int id;
    private static String nama;
    private static String username;
    private static String pass;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        user.id = id;
    }

    public static String getNama() {
        return nama;
    }

    public static void setNama(String nama) {
        user.nama = nama;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        user.username = username;
    }

    public static String getPass() {
        return pass;
    }

    public static void setPass(String pass) {
        user.pass = pass;
    }
}
