package utp.edu.pe.ayapalleckmuchik.dao;

import utp.edu.pe.ayapalleckmuchik.interfaces.ClienteDAOInterface;
import utp.edu.pe.ayapalleckmuchik.model.Cliente;
import utp.edu.pe.ayapalleckmuchik.util.AppConfig;
import utp.edu.pe.ayapalleckmuchik.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements AutoCloseable, ClienteDAOInterface {
    private final Connection cnn;

    public ClienteDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createCliente(Cliente cliente) throws SQLException {
        String query = "{CALL sp_create_cliente(?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setString(1, cliente.getNombre());
            cs.setString(2, cliente.getApellido());
            cs.setString(3, cliente.getTipo_documento());
            cs.setString(4, cliente.getNumero_documento());
            cs.setString(5, cliente.getEmail());
            cs.setString(6, cliente.getTelefono());
            cs.setDate(7, Date.valueOf(cliente.getFecha_nacimiento()));
            cs.execute();
        }
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String query = "{CALL sp_update_cliente(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, cliente.getId_cliente());
            cs.setString(2, cliente.getNombre());
            cs.setString(3, cliente.getApellido());
            cs.setString(4, cliente.getTipo_documento());
            cs.setString(5, cliente.getNumero_documento());
            cs.setString(6, cliente.getEmail());
            cs.setString(7, cliente.getTelefono());
            cs.setDate(8, Date.valueOf(cliente.getFecha_nacimiento()));
            cs.execute();
        }
    }

    public void deleteCliente(int id_cliente) throws SQLException {
        String query = "{CALL sp_delete_cliente(?)}";
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_cliente);
            cs.execute();
        }
    }

    public Cliente getClienteById(int id_cliente) throws SQLException {
        String query = "{CALL sp_get_cliente_by_id(?)}";
        Cliente cliente = null;
        try (CallableStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_cliente);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTipo_documento(rs.getString("tipo_documento"));
                cliente.setNumero_documento(rs.getString("numero_documento"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            }
        }
        return cliente;
    }

    public List<Cliente> getClientes() throws SQLException {
        String query = "{CALL sp_get_all_clientes()}";
        List<Cliente> clientes = new ArrayList<>();
        try (CallableStatement cs = cnn.prepareCall(query)) {
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setTipo_documento(rs.getString("tipo_documento"));
                cliente.setNumero_documento(rs.getString("numero_documento"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public static void main(String[] args) {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            System.out.println(clienteDAO.getClienteById(2));
            clienteDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
