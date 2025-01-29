package utp.edu.pe.ayapalleckmuchik.dao;

import org.checkerframework.checker.units.qual.A;
import utp.edu.pe.ayapalleckmuchik.interfaces.Tipo_habitacionDAOinterface;
import utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion;
import utp.edu.pe.ayapalleckmuchik.util.AppConfig;
import utp.edu.pe.ayapalleckmuchik.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tipo_habitacionDAO implements AutoCloseable, Tipo_habitacionDAOinterface {
    private final Connection cnn;

    public Tipo_habitacionDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createTipoHabitacion(Tipo_habitacion tipoHabitacion) throws SQLException {
        String query = "{CALL sp_create_tipo_habitacion(?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setString(1, tipoHabitacion.getNombre_habitacion());
            cs.setString(2, tipoHabitacion.getDescripcion());
            cs.setDouble(3, tipoHabitacion.getPrecio_noche());
            cs.execute();
        }
    }

    public void updateTipoHabitacion(Tipo_habitacion tipoHabitacion) throws SQLException {
        String query = "{CALL sp_update_tipo_habitacion(?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, tipoHabitacion.getId_tipo_habitacion());
            cs.setString(2, tipoHabitacion.getNombre_habitacion());
            cs.setString(3, tipoHabitacion.getDescripcion());
            cs.setDouble(4, tipoHabitacion.getPrecio_noche());
            cs.execute();
        }
    }

    public void deleteTipoHabitacion(int id_tipo_habitacion) throws SQLException {
        String query = "{CALL sp_delete_tipo_habitacion(?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_tipo_habitacion);
            cs.execute();
        }
    }

    public Tipo_habitacion getTipoHabitacionById(int id_tipo_habitacion) throws SQLException {
        String query = "{CALL sp_get_tipo_habitacion_by_id(?)}";
        Tipo_habitacion tipoHabitacion = null;
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_tipo_habitacion);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                tipoHabitacion = new Tipo_habitacion();
                tipoHabitacion.setId_tipo_habitacion(rs.getInt("id_tipo_habitacion"));
                tipoHabitacion.setNombre_habitacion(rs.getString("nombreHabitacion"));
                tipoHabitacion.setDescripcion(rs.getString("descripcion"));
                tipoHabitacion.setPrecio_noche(rs.getDouble("precio_noche"));
            }
        }
        return tipoHabitacion;
    }

    public List<Tipo_habitacion> getTipoHabitaciones() throws SQLException {
        String query = "{CALL sp_get_all_tipo_habitaciones()}";
        List<Tipo_habitacion> tipoHabitaciones = new ArrayList<>();
        try (CallableStatement cs = cnn.prepareCall(query)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Tipo_habitacion tipoHabitacion = new Tipo_habitacion();
                tipoHabitacion.setId_tipo_habitacion(rs.getInt("id_tipo_habitacion"));
                tipoHabitacion.setNombre_habitacion(rs.getString("nombreHabitacion"));
                tipoHabitacion.setDescripcion(rs.getString("descripcion"));
                tipoHabitacion.setPrecio_noche(rs.getDouble("precio_noche"));
                tipoHabitaciones.add(tipoHabitacion);
            }
        }
        return tipoHabitaciones;
    }
}
