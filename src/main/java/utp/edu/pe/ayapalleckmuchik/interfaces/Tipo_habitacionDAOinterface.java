package utp.edu.pe.ayapalleckmuchik.interfaces;
import utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion;

import java.sql.SQLException;
import java.util.List;


public interface Tipo_habitacionDAOinterface {
    // Método para crear un nuevo tipo de habitación en la base de datos
    void createTipoHabitacion(Tipo_habitacion tipoHabitacion) throws SQLException;

    // Método para actualizar un tipo de habitación en la base de datos
    void updateTipoHabitacion(Tipo_habitacion tipoHabitacion) throws SQLException;

    // Método para eliminar un tipo de habitación usando su ID
    void deleteTipoHabitacion(int id_tipo_habitacion) throws SQLException;

    // Método para obtener un tipo de habitación por su ID
    Tipo_habitacion getTipoHabitacionById(int id_tipo_habitacion) throws SQLException;

    // Método para obtener todos los tipos de habitaciones
    List<Tipo_habitacion> getTipoHabitaciones() throws SQLException;

    // Método para cerrar la conexión
    void close() throws SQLException;
}

