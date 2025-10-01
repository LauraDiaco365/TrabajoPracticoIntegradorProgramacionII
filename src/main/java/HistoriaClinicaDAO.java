import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinicaDAO implements GenericDao<HistoriaClinica> {

    @Override
    public void crear(HistoriaClinica historia, Connection conn) throws Exception {
        String sql = "INSERT INTO historia_clinica (id_historia, eliminado, nro_historia, grupo_sanguineo, antecedentes, medicacion_actual, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, historia.getIdHistoria());
            stmt.setBoolean(2, historia.isEliminado());
            stmt.setString(3, historia.getNroHistoria());
            stmt.setString(4, historia.getGrupoSanguineo());
            stmt.setString(5, historia.getAntecedentes());
            stmt.setString(6, historia.getMedicacionActual());
            stmt.setString(7, historia.getObservaciones());
            stmt.executeUpdate();
        }
    }

    @Override
    public HistoriaClinica leer(long id, Connection conn) throws Exception {
        String sql = "SELECT * FROM historia_clinica WHERE id_historia = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new HistoriaClinica(
                    rs.getInt("id_historia"),
                    rs.getBoolean("eliminado"),
                    rs.getString("nro_historia"),
                    rs.getString("grupo_sanguineo"),
                    rs.getString("antecedentes"),
                    rs.getString("medicacion_actual"),
                    rs.getString("observaciones")
                );
            }
        }
        return null;
    }

    @Override
    public List<HistoriaClinica> leerTodos(Connection conn) throws Exception {
        List<HistoriaClinica> lista = new ArrayList<>();
        String sql = "SELECT * FROM historia_clinica";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HistoriaClinica h = new HistoriaClinica(
                    rs.getInt("id_historia"),
                    rs.getBoolean("eliminado"),
                    rs.getString("nro_historia"),
                    rs.getString("grupo_sanguineo"),
                    rs.getString("antecedentes"),
                    rs.getString("medicacion_actual"),
                    rs.getString("observaciones")
                );
                lista.add(h);
            }
        }
        return lista;
    }

    @Override
    public void actualizar(HistoriaClinica historia, Connection conn) throws Exception {
        String sql = "UPDATE historia_clinica SET nro_historia = ?, grupo_sanguineo = ?, antecedentes = ?, medicacion_actual = ?, observaciones = ?, eliminado = ? WHERE id_historia = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, historia.getNroHistoria());
            stmt.setString(2, historia.getGrupoSanguineo());
            stmt.setString(3, historia.getAntecedentes());
            stmt.setString(4, historia.getMedicacionActual());
            stmt.setString(5, historia.getObservaciones());
            stmt.setBoolean(6, historia.isEliminado());
            stmt.setInt(7, historia.getIdHistoria());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminarLogico(int id, Connection conn) throws Exception {
        String sql = "UPDATE historia_clinica SET eliminado = true WHERE id_historia = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}