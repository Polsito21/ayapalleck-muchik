package utp.edu.pe.ayapalleckmuchik.dao;

import utp.edu.pe.ayapalleckmuchik.model.Habitacion;
import utp.edu.pe.ayapalleckmuchik.util.AppConfig;
import utp.edu.pe.ayapalleckmuchik.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private final Connection cnn;

    public HabitacionDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createHabitacion(Habitacion habitacion) throws SQLException {
        String query = "{CALL sp_create_habitacion(?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setString(1, habitacion.getNumero_habitacion());
            cs.setInt(2, habitacion.getId_tipo_habitacion());
            cs.setString(3, habitacion.getEstado());
            cs.setString(4, habitacion.getEstado_limpieza());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHabitacion(Habitacion habitacion) throws SQLException {
        String query = "{CALL sp_update_habitacion(?, ?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, habitacion.getId_habitacion());
            cs.setString(2, habitacion.getNumero_habitacion());
            cs.setInt(3, habitacion.getId_tipo_habitacion());
            cs.setString(4, habitacion.getEstado());
            cs.setString(5, habitacion.getEstado_limpieza());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHabitacion(int id_habitacion) throws SQLException {
        String query = "{CALL sp_delete_habitacion(?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_habitacion);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Habitacion getHabitacionById(int id_habitacion) throws SQLException {
        String query = "{CALL sp_get_habitacion_by_id(?)}";
        Habitacion habitacion = null;
        try (CallableStatement cs = cnn.prepareCall(query)) {
            Tipo_habitacionDAO tipo_habitacionDAO = new Tipo_habitacionDAO();
            cs.setInt(1, id_habitacion);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                habitacion = new Habitacion();
                habitacion.setId_habitacion(rs.getInt("id_habitacion"));
                habitacion.setNumero_habitacion(rs.getString("numero_habitacion"));
                habitacion.setId_tipo_habitacion(rs.getInt("id_tipo_habitacion"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setEstado_limpieza(rs.getString("estado_limpieza"));
                habitacion.setTipo_habitacion(tipo_habitacionDAO.getTipoHabitacionById(rs.getInt("id_tipo_habitacion")));
            }
            tipo_habitacionDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return habitacion;
    }

    public List<Habitacion> getHabitaciones() throws SQLException {
        String query = "{CALL sp_get_all_habitaciones()}";
        List<Habitacion> habitaciones = new ArrayList<>();
        try (CallableStatement cs = cnn.prepareCall(query)) {
            Tipo_habitacionDAO tipo_habitacionDAO = new Tipo_habitacionDAO();
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Habitacion habitacion = new Habitacion();
                habitacion.setId_habitacion(rs.getInt("id_habitacion"));
                habitacion.setNumero_habitacion(rs.getString("numero_habitacion"));
                habitacion.setId_tipo_habitacion(rs.getInt("id_tipo_habitacion"));
                habitacion.setEstado(rs.getString("estado"));
                habitacion.setEstado_limpieza(rs.getString("estado_limpieza"));
                habitacion.setTipo_habitacion(tipo_habitacionDAO.getTipoHabitacionById(rs.getInt("id_tipo_habitacion")));
                habitaciones.add(habitacion);
            }
            tipo_habitacionDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return habitaciones;
    }
}
