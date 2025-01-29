package utp.edu.pe.ayapalleckmuchik.interfaces;

import utp.edu.pe.ayapalleckmuchik.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAOInterface {


    void createCliente(Cliente cliente) throws SQLException;
    void updateCliente(Cliente cliente) throws SQLException;
    void deleteCliente(int id_cliente) throws SQLException;


    Cliente getClienteById(int id_cliente) throws SQLException;

    List<Cliente> getClientes() throws SQLException;

    void close() throws SQLException;
}

