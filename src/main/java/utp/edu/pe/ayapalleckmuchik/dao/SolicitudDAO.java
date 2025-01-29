package utp.edu.pe.ayapalleckmuchik.dao;

import utp.edu.pe.ayapalleckmuchik.model.Solicitud;
import utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion;
import utp.edu.pe.ayapalleckmuchik.model.enums.Estado;
import utp.edu.pe.ayapalleckmuchik.util.AppConfig;
import utp.edu.pe.ayapalleckmuchik.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitudDAO implements AutoCloseable {
    private final Connection cnn;

    public SolicitudDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createSolicitud(Solicitud solicitud) throws SQLException {
        String query = "{CALL sp_create_solicitud(?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, solicitud.getId_tipo_habitacion());
            cs.setTimestamp(2, Timestamp.valueOf(solicitud.getFecha_solicitud()));
            cs.setTimestamp(3, Timestamp.valueOf(solicitud.getFecha_reserva()));
            cs.setString(4, solicitud.getNombre_cliente());
            cs.setString(5, solicitud.getCorreo());
            cs.setInt(6, solicitud.getDuracion());
            cs.setString(7, solicitud.getEstado().name());
            cs.execute();
        }
    }

    public void updateSolicitud(Solicitud solicitud) throws SQLException {
        String query = "{CALL sp_update_solicitud(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, solicitud.getId_solicitud());
            cs.setInt(2, solicitud.getId_tipo_habitacion());
            cs.setTimestamp(3, Timestamp.valueOf(solicitud.getFecha_solicitud()));
            cs.setTimestamp(4, Timestamp.valueOf(solicitud.getFecha_reserva()));
            cs.setString(5, solicitud.getNombre_cliente());
            cs.setString(6, solicitud.getCorreo());
            cs.setInt(7, solicitud.getDuracion());
            cs.setString(8, solicitud.getEstado().name());
            cs.execute();
        }
    }

    public void deleteSolicitud(int id_solicitud) throws SQLException {
        String query = "{CALL sp_delete_solicitud(?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_solicitud);
            cs.execute();
        }
    }

    public Solicitud getSolicitudById(int id_solicitud) throws SQLException {
        String query = "{CALL sp_get_solicitud_by_id(?)}";
        Solicitud solicitud = null;
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_solicitud);
            ResultSet rs = cs.executeQuery();
            Tipo_habitacionDAO tipo_habitacionDAO = new Tipo_habitacionDAO();
            if (rs.next()) {
                solicitud = new Solicitud();
                solicitud.setId_solicitud(rs.getInt("id_solicitud"));
                solicitud.setId_tipo_habitacion(rs.getInt("id_tipo_habitacion"));
                solicitud.setFecha_solicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                solicitud.setFecha_reserva(rs.getTimestamp("fecha_reserva").toLocalDateTime());
                solicitud.setNombre_cliente(rs.getString("nombre_cliente"));
                solicitud.setCorreo(rs.getString("correo"));
                solicitud.setDuracion(rs.getInt("duracion"));
                solicitud.setEstado(Estado.valueOf(rs.getString("estado")));
                solicitud.setTipoHabitacion(tipo_habitacionDAO.getTipoHabitacionById(rs.getInt("id_tipo_habitacion")));
            }
            tipo_habitacionDAO.close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return solicitud;
    }

    public List<Solicitud> getSolicitudes() throws SQLException {
        String query = "{CALL sp_get_all_solicitudes()}";
        List<Solicitud> solicitudes = new ArrayList<>();
        try (CallableStatement cs = cnn.prepareCall(query)) {
            ResultSet rs = cs.executeQuery();
            Tipo_habitacionDAO tipo_habitacionDAO = new Tipo_habitacionDAO();
            while (rs.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setId_solicitud(rs.getInt("id_solicitud"));
                solicitud.setId_tipo_habitacion(rs.getInt("id_tipo_habitacion"));
                solicitud.setFecha_solicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                solicitud.setFecha_reserva(rs.getTimestamp("fecha_reserva").toLocalDateTime());
                solicitud.setNombre_cliente(rs.getString("nombre_cliente"));
                solicitud.setCorreo(rs.getString("correo"));
                solicitud.setDuracion(rs.getInt("duracion"));
                solicitud.setEstado(Estado.valueOf(rs.getString("estado")));
                solicitud.setTipoHabitacion(tipo_habitacionDAO.getTipoHabitacionById(rs.getInt("id_tipo_habitacion")));
                solicitudes.add(solicitud);
            }
            tipo_habitacionDAO.close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return solicitudes;
    }

    public List<Solicitud> getSolicitudesByEstado(Estado estado) throws SQLException {
        String query = "{CALL sp_get_all_solicitudes_by_estado(?)}";
        List<Solicitud> solicitudes = new ArrayList<>();
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setString(1, estado.name());
            Tipo_habitacionDAO tipo_habitacionDAO = new Tipo_habitacionDAO();
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Solicitud solicitud = new Solicitud();
                solicitud.setId_solicitud(rs.getInt("id_solicitud"));
                solicitud.setId_tipo_habitacion(rs.getInt("id_tipo_habitacion"));
                solicitud.setFecha_solicitud(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                solicitud.setFecha_reserva(rs.getTimestamp("fecha_reserva").toLocalDateTime());
                solicitud.setNombre_cliente(rs.getString("nombre_cliente"));
                solicitud.setCorreo(rs.getString("correo"));
                solicitud.setDuracion(rs.getInt("duracion"));
                solicitud.setEstado(Estado.valueOf(rs.getString("estado")));
                solicitud.setTipoHabitacion(tipo_habitacionDAO.getTipoHabitacionById(rs.getInt("id_tipo_habitacion")));
                solicitudes.add(solicitud);
            }
            tipo_habitacionDAO.close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return solicitudes;
    }

    public static void main(String[] args) {
        try {
            SolicitudDAO solicitudDAO = new SolicitudDAO();
            System.out.println(solicitudDAO.getSolicitudById(2));
            solicitudDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
