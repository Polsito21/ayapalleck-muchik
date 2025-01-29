package utp.edu.pe.ayapalleckmuchik.interfaces;

import utp.edu.pe.ayapalleckmuchik.model.Administrador;

import java.sql.SQLException;
import java.util.List;

public interface AdministradorDAOInterface {

    void createAdministrador(Administrador administrador) throws SQLException;
    void updateAdministrador(Administrador administrador) throws SQLException;
    void deleteAdministrador(int id_admin) throws SQLException;
    Administrador getAdministradorById(int id_admin) throws SQLException;
    List<Administrador> getAdministradores() throws SQLException;
    void close() throws SQLException;
}
