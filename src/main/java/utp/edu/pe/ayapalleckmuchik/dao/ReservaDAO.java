package utp.edu.pe.ayapalleckmuchik.dao;

import utp.edu.pe.ayapalleckmuchik.interfaces.ReservaDAOinterface;
import utp.edu.pe.ayapalleckmuchik.model.Reserva;
import utp.edu.pe.ayapalleckmuchik.util.AppConfig;
import utp.edu.pe.ayapalleckmuchik.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO implements AutoCloseable, ReservaDAOinterface {
    private final Connection cnn;

    public ReservaDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createReserva(Reserva reserva) throws SQLException {
        String query = "{CALL sp_create_reserva(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, reserva.getId_cliente());
            cs.setInt(2, reserva.getId_habitacion());
            cs.setDouble(3, reserva.getMonto_total());
            cs.setString(4, reserva.getMetodo_pago());
            cs.setTimestamp(5, Timestamp.valueOf(reserva.getFecha_ingreso()));
            cs.setTimestamp(6, Timestamp.valueOf(reserva.getFecha_salida()));
            cs.setInt(7, reserva.getId_admin());
            cs.setString(8, reserva.getEstado_reserva());
            cs.execute();
        }
    }

    public void updateReserva(Reserva reserva) throws SQLException {
        String query = "{CALL sp_update_reserva(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, reserva.getId_reserva());
            cs.setInt(2, reserva.getId_cliente());
            cs.setInt(3, reserva.getId_habitacion());
            cs.setDouble(4, reserva.getMonto_total());
            cs.setString(5, reserva.getMetodo_pago());
            cs.setTimestamp(6, Timestamp.valueOf(reserva.getFecha_ingreso()));
            cs.setTimestamp(7, Timestamp.valueOf(reserva.getFecha_salida()));
            cs.setInt(8, reserva.getId_admin());
            cs.setString(9, reserva.getEstado_reserva());
            cs.execute();
        }
    }

    public void deleteReserva(int id_reserva) throws SQLException {
        String query = "{CALL sp_delete_reserva(?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_reserva);
            cs.execute();
        }
    }

    public Reserva getReservaById(int id_reserva) throws SQLException {
        String query = "{CALL sp_get_reserva_by_id(?)}";
        Reserva reserva = null;
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_reserva);
            ClienteDAO clienteDAO = new ClienteDAO();
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                reserva = new Reserva();
                reserva.setId_reserva(rs.getInt("id_reserva"));
                reserva.setId_cliente(rs.getInt("id_cliente"));
                reserva.setId_habitacion(rs.getInt("id_habitacion"));
                reserva.setMonto_total(rs.getDouble("monto_total"));
                reserva.setMetodo_pago(rs.getString("metodo_pago"));
                reserva.setFecha_ingreso(rs.getTimestamp("fecha_ingreso").toLocalDateTime());
                reserva.setFecha_salida(rs.getTimestamp("fecha_salida").toLocalDateTime());
                reserva.setId_admin(rs.getInt("id_admin"));
                reserva.setEstado_reserva(rs.getString("estado_reserva"));
                reserva.setCliente(clienteDAO.getClienteById(reserva.getId_cliente()));
                reserva.setHabitacion(habitacionDAO.getHabitacionById(reserva.getId_habitacion()));
            }
            clienteDAO.close();
            habitacionDAO.close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return reserva;
    }

    public List<Reserva> getReservas() throws SQLException {
        String query = "{CALL sp_get_all_reservas()}";
        List<Reserva> reservas = new ArrayList<>();
        try (CallableStatement cs = cnn.prepareCall(query)) {
            ClienteDAO clienteDAO = new ClienteDAO();
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId_reserva(rs.getInt("id_reserva"));
                reserva.setId_cliente(rs.getInt("id_cliente"));
                reserva.setId_habitacion(rs.getInt("id_habitacion"));
                reserva.setMonto_total(rs.getDouble("monto_total"));
                reserva.setMetodo_pago(rs.getString("metodo_pago"));
                reserva.setFecha_ingreso(rs.getTimestamp("fecha_ingreso").toLocalDateTime());
                reserva.setFecha_salida(rs.getTimestamp("fecha_salida").toLocalDateTime());
                reserva.setId_admin(rs.getInt("id_admin"));
                reserva.setEstado_reserva(rs.getString("estado_reserva"));
                reserva.setCliente(clienteDAO.getClienteById(reserva.getId_cliente()));
                reserva.setHabitacion(habitacionDAO.getHabitacionById(reserva.getId_habitacion()));
                reservas.add(reserva);
            }
            clienteDAO.close();
            habitacionDAO.close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }

    public Reserva getLastReservaByHabitacion(int habitacion_id) throws SQLException {
        String query = "SELECT * FROM reserva WHERE id_habitacion = ? ORDER BY fecha_ingreso DESC LIMIT 1";
        Reserva reserva = null;
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, habitacion_id);
            ResultSet rs = ps.executeQuery();

            ClienteDAO clienteDAO = new ClienteDAO();
            HabitacionDAO habitacionDAO = new HabitacionDAO();

            if (rs.next()) {
                reserva = new Reserva();
                reserva.setId_reserva(rs.getInt("id_reserva"));
                reserva.setId_cliente(rs.getInt("id_cliente"));
                reserva.setId_habitacion(rs.getInt("id_habitacion"));
                reserva.setMonto_total(rs.getDouble("monto_total"));
                reserva.setMetodo_pago(rs.getString("metodo_pago"));
                reserva.setFecha_ingreso(rs.getTimestamp("fecha_ingreso").toLocalDateTime());
                reserva.setFecha_salida(rs.getTimestamp("fecha_salida").toLocalDateTime());
                reserva.setId_admin(rs.getInt("id_admin"));
                reserva.setEstado_reserva(rs.getString("estado_reserva"));

                // Asociar Cliente y Habitacion a la Reserva
                reserva.setCliente(clienteDAO.getClienteById(reserva.getId_cliente()));
                reserva.setHabitacion(habitacionDAO.getHabitacionById(reserva.getId_habitacion()));
            }

            clienteDAO.close();
            habitacionDAO.close();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return reserva;
    }

    public List<Reserva> getReservasByEstado(String estado) {
        List<Reserva> reservas = new ArrayList<>();
        String query = "SELECT * FROM reserva WHERE estado_reserva = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();
            ClienteDAO clienteDAO = new ClienteDAO();
            HabitacionDAO habitacionDAO = new HabitacionDAO();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId_reserva(rs.getInt("id_reserva"));
                reserva.setId_cliente(rs.getInt("id_cliente"));
                reserva.setId_habitacion(rs.getInt("id_habitacion"));
                reserva.setMonto_total(rs.getDouble("monto_total"));
                reserva.setMetodo_pago(rs.getString("metodo_pago"));
                reserva.setFecha_ingreso(rs.getTimestamp("fecha_ingreso").toLocalDateTime());
                reserva.setFecha_salida(rs.getTimestamp("fecha_salida").toLocalDateTime());
                reserva.setId_admin(rs.getInt("id_admin"));
                reserva.setEstado_reserva(rs.getString("estado_reserva"));
                reserva.setCliente(clienteDAO.getClienteById(reserva.getId_cliente()));
                reserva.setHabitacion(habitacionDAO.getHabitacionById(reserva.getId_habitacion()));
                reservas.add(reserva);
            }
            clienteDAO.close();
            habitacionDAO.close();
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
        return reservas;
    }
}
