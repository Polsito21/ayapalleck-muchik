package utp.edu.pe.ayapalleckmuchik.interfaces;

import utp.edu.pe.ayapalleckmuchik.model.Solicitud;
import java.sql.SQLException;
import java.util.List;

public interface SolicitudDAOInterface {
    void createSolicitud(Solicitud solicitud) throws SQLException;
    void updateSolicitud(Solicitud solicitud) throws SQLException;
    void deleteSolicitud(int id_solicitud) throws SQLException;
    Solicitud getSolicitudById(int id_solicitud) throws SQLException;
    List<Solicitud> getAllSolicitudes() throws SQLException;
}
