package com.mycompany.trabajopractico.main;

import com.mycompany.trabajopractico.dao.PacienteDAO;
import com.mycompany.trabajopractico.entities.Paciente;
import com.mycompany.trabajopractico.config.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PacienteDAO dao = new PacienteDAO();

            // 🔐 Prueba anti-inyección SQL
            System.out.println("🔐 Ejecutando prueba anti-inyección SQL...");

            String dniMalicioso = "' OR '1'='1";

            Paciente resultado = dao.buscarPorDni(dniMalicioso, conn);

            if (resultado == null) {
                System.out.println("✅ Consulta segura: no se devolvió ningún paciente con entrada maliciosa.");
            } else {
                System.out.println("⚠️ Riesgo: se devolvió un paciente con entrada maliciosa.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar con la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }

}