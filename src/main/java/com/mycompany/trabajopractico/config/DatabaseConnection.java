package com.mycompany.trabajopractico.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tfi_gestion_clinica";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "mellis2610";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}