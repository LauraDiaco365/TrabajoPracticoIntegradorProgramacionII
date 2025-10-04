package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class PruebaConexion {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Driver cargado correctamente.");

            String url = "jdbc:mysql://localhost:3306/tp_pacientes";
            String usuario = "root";
            String contraseña = "mellis2610";

            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("✅ Conexión exitosa a la base de datos.");

            conexion.close();
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: Driver no encontrado.");
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
    }
}
