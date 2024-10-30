package utp.edu.pe.ayapalleckmuchik.dao;

import utp.edu.pe.ayapalleckmuchik.model.Administrador;
import utp.edu.pe.ayapalleckmuchik.service.Auth;
import utp.edu.pe.ayapalleckmuchik.util.AppConfig;
import utp.edu.pe.ayapalleckmuchik.util.DataAccessMariaDB;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {
    private final Connection cnn;

    public AdministradorDAO() throws SQLException, NamingException {
        this.cnn = DataAccessMariaDB.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccessMariaDB.closeConnection(this.cnn);
    }

    public void createAdministrador(Administrador administrador) throws SQLException {
        String query = "INSERT INTO administrador (usuario, password) VALUES (?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, administrador.getUsuario());
            ps.setString(2, Auth.md5(administrador.getPassword()));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAdministrador(Administrador administrador) throws SQLException {
        String query = "UPDATE administrador SET usuario = ?, password = ? WHERE id_admin = ?";
        try (PreparedStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, administrador.getId_admin());
            cs.setString(2, administrador.getUsuario());
            cs.setString(3, Auth.md5(administrador.getPassword()));
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAdministrador(int id_admin) throws SQLException {
        String query = "DELETE FROM administrador WHERE id_admin = ?";
        try (PreparedStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_admin);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Administrador getAdministradorById(int id_admin) throws SQLException {
        String query = "SELECT * FROM administrador WHERE id_admin = ?";
        Administrador administrador = null;
        try (PreparedStatement cs = cnn.prepareCall(query)) {
            cs.setInt(1, id_admin);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                administrador = new Administrador();
                administrador.setId_admin(rs.getInt("id_admin"));
                administrador.setUsuario(rs.getString("usuario"));
                administrador.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrador;
    }

    public List<Administrador> getAdministradores() throws SQLException {
        String query = "SELECT * FROM administrador";
        List<Administrador> administradores = new ArrayList<>();
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Administrador administrador = new Administrador();
                administrador.setId_admin(rs.getInt("id_admin"));
                administrador.setUsuario(rs.getString("usuario"));
                administrador.setPassword(rs.getString("password"));
                administradores.add(administrador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administradores;
    }

    public static void main(String[] args) {
        Administrador administrator1 = new Administrador("florespaul", "123456");
        Administrador administrator2 = new Administrador("diazabigail", "123456");
        Administrador administrator3 = new Administrador("eliasfabrizio", "123456");
        AdministradorDAO administradorDAO;
        try {
            administradorDAO = new AdministradorDAO();
            administradorDAO.createAdministrador(administrator1);
            administradorDAO.createAdministrador(administrator2);
            administradorDAO.createAdministrador(administrator3);
            System.out.println(administradorDAO.getAdministradores());
            administradorDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
