package utp.edu.pe.ayapalleckmuchik.interfaces;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;

import java.sql.SQLException;
import java.util.List;

public interface HabitacionDAOinterface {

    void createHabitacion(Habitacion habitacion) throws SQLException;


    void updateHabitacion(Habitacion habitacion) throws SQLException;


    void deleteHabitacion(int id_habitacion) throws SQLException;


    Habitacion getHabitacionById(int id_habitacion) throws SQLException;


    List<Habitacion> getHabitaciones() throws SQLException;

    // Método para cerrar la conexión
    void close() throws SQLException;
}