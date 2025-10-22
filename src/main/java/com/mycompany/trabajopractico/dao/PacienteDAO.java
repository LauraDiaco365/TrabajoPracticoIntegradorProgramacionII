package com.mycompany.trabajopractico.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.trabajopractico.entities.Paciente;
import com.mycompany.trabajopractico.entities.HistoriaClinica;


public class PacienteDAO implements GenericDao<Paciente> {

    @Override
    public void crear(Paciente paciente, Connection conn) throws Exception {
        String sql = "INSERT INTO paciente (id_paciente, nombre, apellido, dni, fecha_nacimiento, eliminado, id_historia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paciente.getIdPaciente());
            stmt.setString(2, paciente.getNombre());
            stmt.setString(3, paciente.getApellido());
            stmt.setString(4, paciente.getDni());
            stmt.setDate(5, Date.valueOf(paciente.getFechaNacimiento()));
            stmt.setBoolean(6, paciente.isEliminado());
            stmt.setInt(7, paciente.getHistoria().getIdHistoria());
            stmt.executeUpdate();
        }
    }

    @Override
    public Paciente leer(long id, Connection conn) throws Exception {
        String sql = "SELECT * FROM paciente WHERE id_paciente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                HistoriaClinicaDAO daoHistoria = new HistoriaClinicaDAO();
                HistoriaClinica historia = daoHistoria.leer(rs.getInt("id_historia"), conn);

                return new Paciente(
                    rs.getInt("id_paciente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getBoolean("eliminado"),
                    historia
                );
            }
        }
        return null;
    }

    @Override
    public List<Paciente> leerTodos(Connection conn) throws Exception {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            HistoriaClinicaDAO daoHistoria = new HistoriaClinicaDAO();
            while (rs.next()) {
                HistoriaClinica historia = daoHistoria.leer(rs.getInt("id_historia"), conn);
                Paciente p = new Paciente(
                    rs.getInt("id_paciente"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getBoolean("eliminado"),
                    historia
                );
                lista.add(p);
            }
        }
        return lista;
    }

    @Override
    public void actualizar(Paciente paciente, Connection conn) throws Exception {
        String sql = "UPDATE paciente SET nombre = ?, apellido = ?, dni = ?, fecha_nacimiento = ?, eliminado = ?, id_historia = ? WHERE id_paciente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, paciente.getNombre());
            stmt.setString(2, paciente.getApellido());
            stmt.setString(3, paciente.getDni());
            stmt.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));
            stmt.setBoolean(5, paciente.isEliminado());
            stmt.setInt(6, paciente.getHistoria().getIdHistoria());
            stmt.setInt(7, paciente.getIdPaciente());
            stmt.executeUpdate();
        }
    }

@Override
public void eliminarLogico(int id, Connection conn) throws Exception {
    String sql = "UPDATE paciente SET eliminado = true WHERE id_paciente = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}

// Consulta segura por DNI para prevenir inyección SQL
public Paciente buscarPorDni(String dni, Connection conn) throws Exception {
    String sql = "SELECT * FROM paciente WHERE dni = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, dni);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            HistoriaClinicaDAO daoHistoria = new HistoriaClinicaDAO();
            HistoriaClinica historia = daoHistoria.leer(rs.getInt("id_historia"), conn);
            return new Paciente(
                rs.getInt("id_paciente"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("dni"),
                rs.getDate("fecha_nacimiento").toLocalDate(),
                rs.getBoolean("eliminado"),
                historia
            );
        }
    }
    return null;
}
} 